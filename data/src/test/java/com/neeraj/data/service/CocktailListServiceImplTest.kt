package com.neeraj.data.service

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.common.util.InternetUtil
import com.neeraj.data.constant.cocktailListModel
import com.neeraj.data.constant.cocktailListResponseModel
import com.neeraj.data.constant.internalServerError
import com.neeraj.data.constant.mapperError
import com.neeraj.data.mapper.cocktaillist.CocktailListMapper
import com.neeraj.data.model.cocktaillist.CocktailListResponseModel
import com.neeraj.data.network.CocktailApiService
import com.neeraj.data.service.cocktaillist.CocktailListServiceImpl
import com.neeraj.data.util.TestUtils
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
class CocktailListServiceImplTest {

    private lateinit var cocktailListServiceImpl: CocktailListServiceImpl
    private val testDispatcher = StandardTestDispatcher()

    // Mock dependencies
    private val mockCocktailApiService = mockk<CocktailApiService>()
    private val mockCocktailListMapper = mockk<CocktailListMapper>()
    private val mockInternetUtil = mockk<InternetUtil>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        // Create an instance of the CocktailListServiceImpl using the mock dependencies
        cocktailListServiceImpl = CocktailListServiceImpl(
            cocktailApiService = mockCocktailApiService,
            cocktailListMapper = mockCocktailListMapper,
            internetUtil = mockInternetUtil
        )
    }

    @Test
    fun `fetchCocktailList success response should emit Resources Success`() = runTest {

        val cocktailListResponseModel = TestUtils.convertJsonToResponseModel(
            TestUtils.getJsonFile(cocktailListResponseModel),
            CocktailListResponseModel::class.java
        )

        val cocktailListModel =
            TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))


        coEvery { mockInternetUtil.isInternetAvailable() } returns true
        coEvery { mockCocktailApiService.getCocktailList() } returns cocktailListResponseModel
        coEvery {
            cocktailListResponseModel.body()
                ?.let { mockCocktailListMapper.getCocktailList(it) }
        } returns cocktailListModel


        val result = cocktailListServiceImpl.fetchCocktailList().first()


        assertEquals(result, cocktailListModel)

    }

    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)
            // Arrange
            every { mockInternetUtil.isInternetAvailable() } returns false

            // Act
            val exceptedResult = cocktailListServiceImpl.fetchCocktailList().first()

            // Assert
            assertEquals(
                expectedException.message, (exceptedResult as Resources.Failure).exception.message
            )
        }


    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure api exception`() =
        runTest {

            val cocktailListResponseModel = TestUtils.convertJsonToResponseModel(TestUtils.getJsonFile(cocktailListResponseModel), CocktailListResponseModel::class.java)
            val apiException = Response.error<CocktailListResponseModel>(500, internalServerError.toResponseBody())

            // Arrange
            coEvery { mockInternetUtil.isInternetAvailable() } returns true
            coEvery { mockCocktailApiService.getCocktailList() } returns cocktailListResponseModel
            coEvery { mockCocktailApiService.getCocktailList().isSuccessful } returns false
            coEvery { mockCocktailApiService.getCocktailList() } returns apiException

            // Act
            val result = mockCocktailApiService.getCocktailList()

            // Assert
            assertEquals(apiException.errorBody().toString(), result.errorBody().toString())

        }


        @Test
        fun `fetchCocktailList data exception should emit Resources_Failure_DataException`() = runTest {
            // Arrange
           val exception = DataException(mapperError)
            val cocktailListResponseModel = TestUtils.convertJsonToResponseModel(TestUtils.getJsonFile(cocktailListResponseModel), CocktailListResponseModel::class.java)

           // Arrange
           coEvery { mockInternetUtil.isInternetAvailable() } returns true
           coEvery { mockCocktailApiService.getCocktailList() } returns cocktailListResponseModel
            coEvery { cocktailListResponseModel.body()
                ?.let { mockCocktailListMapper.getCocktailList(it) } } returns Resources.Failure(exception)

           // Act
           val result = cocktailListServiceImpl.fetchCocktailList().first()

           // Assert
           assertEquals(exception.message, (result as Resources.Failure).exception.message)

        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
