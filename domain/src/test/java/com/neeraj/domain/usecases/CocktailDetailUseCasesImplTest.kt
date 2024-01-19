package com.neeraj.domain.usecases

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.domain.constant.cocktailDetailModel
import com.neeraj.domain.constant.cocktailId
import com.neeraj.domain.constant.internalServerError
import com.neeraj.domain.constant.mapperError
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import com.neeraj.domain.usecases.cocktaildetail.CocktailDetailUseCasesImpl
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

class CocktailDetailUseCasesImplTest {

    private lateinit var mockCocktailDetailRepository: CocktailDetailRepository
    private lateinit var cocktailDetailUseCasesImpl: CocktailDetailUseCasesImpl


    @Before
    fun setUp() {
        mockCocktailDetailRepository = mockk<CocktailDetailRepository>()
        cocktailDetailUseCasesImpl = CocktailDetailUseCasesImpl(mockCocktailDetailRepository)
    }


    @Test
    fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return flow resource cocktail detail model`() =
        runTest {

            // GIVEN
            val cocktailDetailModel = TestUtils.parseJSONToCocktailDetail(TestUtils.getJsonFile(cocktailDetailModel))

            coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns flowOf(cocktailDetailModel)

            // Act
            val result = mutableListOf<Resources<CocktailDetailModel>>()

            cocktailDetailUseCasesImpl.invoke(cocktailId).collect { result.add(it) }

            // Assert
            assertEquals(cocktailDetailModel, result.first())
        }


     @Test
     fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure network exception`() = runTest {

         val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

         coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(expectedException))

         val result = cocktailDetailUseCasesImpl.invoke(cocktailId).first()

         assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
     }


    @Test
     fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure api exception`() = runTest {

     val exception = HttpException(Response.error<ResponseBody>(500, internalServerError.toResponseBody()))

         coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(exception))

         val result = cocktailDetailUseCasesImpl.invoke(cocktailId).first()

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

  @Test
     fun `GIVEN cocktail detail repository WHEN fetchCocktailDetail is called THEN return resource failure data exception`() = runTest {

         val exception = DataException(mapperError)

         coEvery { mockCocktailDetailRepository.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(exception))

         val result = cocktailDetailUseCasesImpl.invoke(cocktailId).first()

         assertEquals(exception.message, (result as Resources.Failure).exception.message)
     }

}