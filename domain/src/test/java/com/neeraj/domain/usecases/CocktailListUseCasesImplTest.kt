package com.neeraj.domain.usecases


import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.domain.constant.cocktailListModel
import com.neeraj.domain.constant.internalServerError
import com.neeraj.domain.constant.mapperError
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import com.neeraj.domain.usecases.cocktaillist.CocktailListUseCasesImpl
import com.neeraj.domain.util.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
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
    fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return flow resource cocktail list model`() =
        runTest {

            // GIVEN
            val cocktailListModel =
                TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))

            coEvery { mockCocktailListRepository.fetchCocktailList() } returns flowOf(cocktailListModel)

            // Act
            val result = mutableListOf<Resources<List<CocktailListModel>>>()

            cocktailListUseCasesImpl.invoke().collect { result.add(it) }

            // Assert
            assertEquals(cocktailListModel, result.first())
        }

    @Test
    fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return resource failure network exception`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailListRepository.fetchCocktailList() } returns flowOf(Resources.Failure(expectedException))

        val result = cocktailListUseCasesImpl.invoke().first()

        assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
    }


    @Test
     fun `GIVEN cocktail detail repository WHEN fetchCocktailList is called THEN return resource failure api exception`() = runTest {

     val exception = HttpException(Response.error<ResponseBody>(500, internalServerError.toResponseBody()))

         coEvery { mockCocktailListRepository.fetchCocktailList() } returns flowOf(Resources.Failure(exception))

         val result = cocktailListUseCasesImpl.invoke().first()

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

    @Test
     fun `GIVEN cocktail list repository WHEN fetchCocktailList is called THEN return resource failure data exception`() = runTest {

         val exception = DataException(mapperError)

         coEvery { mockCocktailListRepository.fetchCocktailList() } returns flowOf(Resources.Failure(exception))

         val result = cocktailListUseCasesImpl.invoke().first()

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

}