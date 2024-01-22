package com.sapi.domain.usecases


import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.domain.constant.cocktailListModel
import com.sapi.domain.constant.internalServerError
import com.sapi.domain.constant.mapperError
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
import com.sapi.domain.usecases.cocktaillist.CocktailListUseCasesImpl
import com.sapi.domain.util.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class CocktailListUseCasesImplTest {

    private lateinit var mockCocktailListRepository: CocktailListRepository
    private lateinit var cocktailListUseCasesImpl: CocktailListUseCasesImpl


    @Before
    fun setUp() {
        mockCocktailListRepository = mockk<CocktailListRepository>()
        cocktailListUseCasesImpl = CocktailListUseCasesImpl(mockCocktailListRepository)
    }


    @Test
    fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return resource cocktail list model`() =
        runTest {

            val cocktailListModel =
                TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))

            coEvery { mockCocktailListRepository.fetchCocktailList() } returns cocktailListModel
            val result =  cocktailListUseCasesImpl.invoke()

            assertEquals(cocktailListModel, result)
        }

    @Test
    fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return resource failure network exception`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailListRepository.fetchCocktailList() } returns Resources.Failure(expectedException)

        val result = cocktailListUseCasesImpl.invoke()

        assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
    }


    @Test
     fun `GIVEN cocktail detail repository WHEN fetchCocktailList is called THEN return resource failure api exception`() = runTest {

     val exception = HttpException(Response.error<ResponseBody>(500, internalServerError.toResponseBody()))

         coEvery { mockCocktailListRepository.fetchCocktailList() } returns Resources.Failure(exception)

         val result = cocktailListUseCasesImpl.invoke()

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

    @Test
     fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return resource failure data exception`() = runTest {

        val exception = DataException(mapperError)

        coEvery { mockCocktailListRepository.fetchCocktailList() } returns Resources.Failure(
            exception
        )

        val result = cocktailListUseCasesImpl.invoke()

        assertEquals(exception.message, (result as Resources.Failure).exception.message)
    }
}