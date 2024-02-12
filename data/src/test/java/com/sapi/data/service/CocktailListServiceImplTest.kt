package com.sapi.data.service

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import com.sapi.data.constant.cocktailListJson
import com.sapi.data.constant.cocktailListResponseJson
import com.sapi.data.constant.internalServerError
import com.sapi.data.mapper.cocktaillist.CocktailListMapper
import com.sapi.data.model.cocktaillist.CocktailListResponse
import com.sapi.data.network.CocktailApiService
import com.sapi.data.service.cocktaillist.CocktailListServiceImpl
import com.sapi.data.util.TestUtils
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CocktailListServiceImplTest {

    private lateinit var cocktailListServiceImpl: CocktailListServiceImpl

    private val testDispatcher = UnconfinedTestDispatcher()

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
            dispatcher = testDispatcher,
            cocktailListMapper = mockCocktailListMapper,
            internetUtil = mockInternetUtil
        )
    }

    @Test
    fun `fetchCocktailList success response should emit Resources Success`() = runTest {

        val cocktailListResponse = TestUtils.convertJsonToResponseModel(
            TestUtils.getJsonFile(cocktailListResponseJson),
            CocktailListResponse::class.java
        )

        val cocktailListModel =
            TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListJson))


        coEvery { mockInternetUtil.isInternetAvailable() } returns true
        coEvery { mockCocktailApiService.getCocktailList() } returns cocktailListResponse
        coEvery {
            cocktailListResponse.body()
                ?.let { mockCocktailListMapper.getCocktailList(it) }
        } returns cocktailListModel


        val result = cocktailListServiceImpl.fetchCocktailList()


        assertEquals(result, Resources.Success(cocktailListModel))

    }

    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)
            // Arrange
            every { mockInternetUtil.isInternetAvailable() } returns false

            // Act
            val exceptedResult = cocktailListServiceImpl.fetchCocktailList()

            // Assert
            assertEquals(
                expectedException.message, (exceptedResult as Resources.Failure).exception.message
            )
        }


    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure api exception`() =
        runTest {

            val cocktailListResponseModel = TestUtils.convertJsonToResponseModel(
                TestUtils.getJsonFile(cocktailListResponseJson),
                CocktailListResponse::class.java
            )
            val apiException =
                Response.error<CocktailListResponse>(500, internalServerError.toResponseBody())

            coEvery { mockInternetUtil.isInternetAvailable() } returns true
            coEvery { mockCocktailApiService.getCocktailList() } returns cocktailListResponseModel
            coEvery { mockCocktailApiService.getCocktailList().isSuccessful } returns false
            coEvery { mockCocktailApiService.getCocktailList() } returns apiException

            val result = mockCocktailApiService.getCocktailList()

            assertEquals(apiException.errorBody().toString(), result.errorBody().toString())
        }

    @Test
    fun `fetchCocktailList data exception should emit Resources_Failure_DataException`() = runTest {

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
        val result = cocktailListServiceImpl.fetchCocktailList()

        assertEquals(dataException.message, (result as Resources.Failure).exception.message)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
