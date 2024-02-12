package com.sapi.data.service

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import com.sapi.data.constant.cocktailDetailResponseJson
import com.sapi.data.constant.cocktailId
import com.sapi.data.constant.internalServerError
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.data.network.CocktailApiService
import com.sapi.data.service.cocktaildetail.CocktailDetailServiceImpl
import com.sapi.data.util.TestUtils
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CocktailDetailServiceImplTest {

    private lateinit var cocktailDetailServiceImpl: CocktailDetailServiceImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    // Mock dependencies
    private val mockCocktailApiService = mockk<CocktailApiService>()
    private val mockCocktailDetailMapper = mockk<CocktailDetailMapper>()
    private val mockInternetUtil = mockk<InternetUtil>()
    private val mockCocktailDetailResponse = mockk<CocktailDetailResponse>()
    private val mockCocktailDetailModel = mockk<CocktailDetail>()

    @Before
    fun setUp() {
        // Create an instance of the CocktailListServiceImpl using the mock dependencies
        cocktailDetailServiceImpl = CocktailDetailServiceImpl(
            cocktailApiService = mockCocktailApiService,
            dispatcher = testDispatcher,
            cocktailDetailMapper = mockCocktailDetailMapper,
            internetUtil = mockInternetUtil
        )
    }

    @Test
    fun `GIVEN apiService, detailMapper and internetUtil WHEN cocktailDetailServiceImpl called THEN return success result`() =
        runTest {

            coEvery { mockInternetUtil.isInternetAvailable() } returns true
            coEvery { mockCocktailApiService.getCocktailDetail(cocktailId) } returns Response.success(
                mockCocktailDetailResponse
            )
            coEvery {
                Response.success(mockCocktailDetailResponse).body()
                    ?.let { mockCocktailDetailMapper.getCocktailDetail(it) }
            } returns mockCocktailDetailModel
            val result = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId)


            assertEquals(result, Resources.Success(mockCocktailDetailModel))
        }

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

            every { mockInternetUtil.isInternetAvailable() } returns false


            val exceptedResult = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId)


            assertEquals(
                expectedException.message, (exceptedResult as Resources.Failure).exception.message
            )
        }


    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure api exception`() =
        runTest {

            val cocktailDetailResponseModel = TestUtils.convertJsonToResponseModel(
                TestUtils.getJsonFile(
                    cocktailDetailResponseJson
                ), CocktailDetailResponse::class.java
            )
            val apiException =
                Response.error<CocktailDetailResponse>(500, internalServerError.toResponseBody())

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

        val dataException = DataException(CommonConstant.ErrorMessage)
        val exception = retrofit2.HttpException(
            Response.error<ResponseBody>(
                503,
                "Address no found".toResponseBody("plain/text".toMediaTypeOrNull())
            )
        )

        coEvery { mockInternetUtil.isInternetAvailable() } returns true
        coEvery { mockCocktailApiService.getCocktailList() } coAnswers {
            throw exception
        }
        val result = cocktailDetailServiceImpl.fetchCocktailDetail(cocktailId)

        assertEquals(dataException.message, (result as Resources.Failure).exception.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
