package com.neeraj.data.repository

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.data.constant.cocktailDetailModel
import com.neeraj.data.constant.cocktailId
import com.neeraj.data.constant.internalServerError
import com.neeraj.data.constant.mapperError
import com.neeraj.data.repository.cocktaildetail.CocktailDetailRepositoryImpl
import com.neeraj.data.service.cocktaildetail.CocktailDetailService
import com.neeraj.data.util.TestUtils
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
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

class CocktailDetailRepositoryImplTest {

    private lateinit var mockCocktailDetailService: CocktailDetailService
    private lateinit var cocktailDetailRepositoryImpl: CocktailDetailRepositoryImpl


    @Before
    fun setUp() {
        mockCocktailDetailService = mockk<CocktailDetailService>()
        cocktailDetailRepositoryImpl = CocktailDetailRepositoryImpl(mockCocktailDetailService)
    }


    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return flow resource cocktail detail model`() =
        runTest {

            // GIVEN
            val cocktailDetailModel =
                TestUtils.parseJSONToCocktailDetail(TestUtils.getJsonFile(cocktailDetailModel))

            coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns flowOf(cocktailDetailModel)

            // Act
            val result = mutableListOf<Resources<CocktailDetailModel>>()

            cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId).collect { result.add(it) }

            // Assert
            assertEquals(cocktailDetailModel, result.first())
        }

    @Test
    fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure network exception`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(expectedException))

        val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId).first()

        assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
    }


      @Test
      fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure api exception`() = runTest {

      val exception = HttpException(Response.error<ResponseBody>(500, internalServerError.toResponseBody()))

          coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(exception))

          val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId).first()

          assertEquals(exception.message, (result as Resources.Failure).exception.message)
      }

       @Test
       fun `GIVEN cocktail detail service WHEN fetchCocktailDetail is called THEN return resource failure data exception`() = runTest {

           val exception = DataException(mapperError)

           coEvery { mockCocktailDetailService.fetchCocktailDetail(cocktailId) } returns flowOf(Resources.Failure(exception))

           val result = cocktailDetailRepositoryImpl.fetchCocktailDetail(cocktailId).first()

           assertEquals(exception.message, (result as Resources.Failure).exception.message)
       }

}