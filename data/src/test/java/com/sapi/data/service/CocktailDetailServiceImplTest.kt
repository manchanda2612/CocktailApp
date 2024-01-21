package com.sapi.data.service

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import com.sapi.data.constant.cocktailDetailModel
import com.sapi.data.constant.cocktailDetailResponseModel
import com.sapi.data.constant.cocktailId
import com.sapi.data.constant.internalServerError
import com.sapi.data.constant.mapperError
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.data.network.CocktailApiService
import com.sapi.data.service.cocktaildetail.CocktailDetailServiceImpl
import com.sapi.data.util.TestUtils
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CocktailDetailServiceImplTest {

    private lateinit var cocktailDetailServiceImpl : CocktailDetailServiceImpl
    private val testDispatcher = StandardTestDispatcher()

    // Mock dependencies
    private val mockCocktailApiService = mockk<CocktailApiService>()
    private val mockCocktailDetailMapper = mockk<CocktailDetailMapper>()
    private val mockInternetUtil = mockk<InternetUtil>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        // Create an instance of the CocktailListServiceImpl using the mock dependencies
        cocktailDetailServiceImpl = CocktailDetailServiceImpl(
            cocktailApiService = mockCocktailApiService,
            cocktailDetailMapper = mockCocktailDetailMapper,
            internetUtil = mockInternetUtil
        )
    }

    @Test
    fun `GIVEN apiService, detailMapper and internetUtil WHEN cocktailDetailServiceImpl called THEN return success result`() = runTest {

        // GIVEN
        val cocktailDetailResponseModel = TestUtils.convertJsonToResponseModel(
            TestUtils.getJsonFile(cocktailDetailResponseModel),
            CocktailDetailResponse::class.java
        )

        val cocktailDetailModel = TestUtils.parseJSONToCocktailDetail(TestUtils.getJsonFile(cocktailDetailModel))

        // WHEN
        coEvery { mockInternetUtil.isInternetAvailable() } returns true
        coEvery { mockCocktailApiService.getCocktailDetail(cocktailId) } returns cocktailDetailResponseModel
        coEvery { cocktailDetailResponseModel.body()
            ?.let { mockCocktailDetailMapper.getCocktailDetail(it) } } returns cocktailDetailModel

        // THEN
        val result = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId).first()

        // Assert
        assertEquals(result, cocktailDetailModel)

}

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)
            // Arrange
            every { mockInternetUtil.isInternetAvailable() } returns false

            // Act
            val exceptedResult = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId).first()

            // Assert
            assertEquals(
                expectedException.message, (exceptedResult as Resources.Failure).exception.message
            )
        }

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure api exception`() =
        runTest {

            val cocktailDetailResponseModel = TestUtils.convertJsonToResponseModel(TestUtils.getJsonFile(
                cocktailDetailResponseModel
            ), CocktailDetailResponse::class.java)
            val apiException = Response.error<CocktailDetailResponse>(500, internalServerError.toResponseBody())

            // Arrange
            coEvery { mockInternetUtil.isInternetAvailable() } returns true
            coEvery { mockCocktailApiService.getCocktailDetail(cocktailId) } returns cocktailDetailResponseModel
            coEvery { mockCocktailApiService.getCocktailDetail(cocktailId).isSuccessful } returns false
            coEvery { mockCocktailApiService.getCocktailDetail(cocktailId) } returns apiException

            // Act
            val result = mockCocktailApiService.getCocktailDetail(cocktailId)

            // Assert
            assertEquals(apiException.errorBody().toString(), result.errorBody().toString())

        }


    @Test
    fun `fetchCocktailDetail data exception should emit Resources_Failure_DataException`() = runTest {
        // Arrange
        val exception = DataException(mapperError)
        val cocktailDetailResponseModel = TestUtils.convertJsonToResponseModel(TestUtils.getJsonFile(
            cocktailDetailResponseModel
        ), CocktailDetailResponse::class.java)

        // Arrange
        coEvery { mockInternetUtil.isInternetAvailable() } returns true
        coEvery { mockCocktailApiService.getCocktailDetail(cocktailId) } returns cocktailDetailResponseModel
        coEvery { cocktailDetailResponseModel.body()
            ?.let { mockCocktailDetailMapper.getCocktailDetail(it) } } returns Resources.Failure(exception)

        // Act
        val result = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId).first()

        // Assert
        assertEquals(exception.message, (result as Resources.Failure).exception.message)

    }




    @After
fun tearDown() {
    Dispatchers.resetMain()
    }
}
