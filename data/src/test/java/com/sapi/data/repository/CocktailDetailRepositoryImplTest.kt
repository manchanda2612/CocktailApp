package com.sapi.data.repository

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.data.constant.cocktailId
import com.sapi.data.constant.internalServerError
import com.sapi.data.constant.mapperError
import com.sapi.data.repository.cocktaildetail.CocktailDetailRepositoryImpl
import com.sapi.data.service.cocktaildetail.CocktailDetailService
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class CocktailDetailRepositoryImplTest {

    private lateinit var mockCocktailDetailService: CocktailDetailService
    private val mockCocktailDetail = mockk<CocktailDetail>()
    private lateinit var cocktailDetailRepositoryImpl: CocktailDetailRepositoryImpl


    @Before
    fun setUp() {
        mockCocktailDetailService = mockk<CocktailDetailService>()
        cocktailDetailRepositoryImpl = CocktailDetailRepositoryImpl(mockCocktailDetailService)
    }


    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource cocktail detail model`() =
        runTest {


            coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns Resources.Success(
                mockCocktailDetail
            )
            val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId)

            assertEquals(Resources.Success(mockCocktailDetail), result)
        }

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

            coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns Resources.Failure(
                expectedException
            )

            val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId)

            assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
        }


    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure api exception`() =
        runTest {

            val exception = HttpException(
                Response.error<ResponseBody>(
                    500,
                    internalServerError.toResponseBody()
                )
            )

            coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns Resources.Failure(
                exception
            )

            val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId)

            assertEquals(exception.message, (result as Resources.Failure).exception.message)
        }

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure data exception`() =
        runTest {

            val exception = DataException(mapperError)

            coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns Resources.Failure(
                exception
            )

            val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId)

            assertEquals(exception.message, (result as Resources.Failure).exception.message)
        }

}