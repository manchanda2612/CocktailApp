package com.sapi.domain.usecases

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.domain.constant.cocktailId
import com.sapi.domain.constant.internalServerError
import com.sapi.domain.constant.mapperError
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import com.sapi.domain.usecases.cocktaildetail.CocktailDetailUseCasesImpl
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
class CocktailDetailUseCasesImplTest {

    private lateinit var cocktailDetailUseCasesImpl: CocktailDetailUseCasesImpl
    private val mockCocktailDetailRepository: CocktailDetailRepository =
        mockk<CocktailDetailRepository>()
    private val mockCocktailDetail = mockk<CocktailDetail>()


    @Before
    fun setUp() {
        cocktailDetailUseCasesImpl = CocktailDetailUseCasesImpl(mockCocktailDetailRepository)
    }


    @Test
    fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource cocktail detail model`() =
        runTest {

            coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns Resources.Success(
                mockCocktailDetail
            )


            val result = cocktailDetailUseCasesImpl.invoke(cocktailId)

            // Assert
            assertEquals(Resources.Success(mockCocktailDetail), result)
        }


    @Test
    fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure network exception`() =
        runTest {

            val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

            coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns Resources.Failure(
                expectedException
            )

            val result = cocktailDetailUseCasesImpl.invoke(cocktailId)

            assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
        }


    @Test
    fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure api exception`() =
        runTest {

            val exception = HttpException(
                Response.error<ResponseBody>(
                    500,
                    internalServerError.toResponseBody()
                )
            )

            coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns Resources.Failure(
                exception
            )

            val result = cocktailDetailUseCasesImpl.invoke(cocktailId)

            assertEquals(exception.message, (result as Resources.Failure).exception.message)
        }

    @Test
    fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure data exception`() =
        runTest {

         val exception = DataException(mapperError)

         coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns Resources.Failure(exception)

         val result = cocktailDetailUseCasesImpl.invoke(cocktailId)

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

}