package com.neeraj.data.repository

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.data.constant.cocktailListModel
import com.neeraj.data.constant.internalServerError
import com.neeraj.data.constant.mapperError
import com.neeraj.data.repository.cocktaillist.CocktailListRepositoryImpl
import com.neeraj.data.service.cocktaillist.CocktailListService
import com.neeraj.data.util.TestUtils
import com.neeraj.domain.model.cocktaillist.CocktailListModel
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

class CocktailListRepositoryImplTest {

    private lateinit var mockCocktailListService: CocktailListService
    private lateinit var cocktailListRepositoryImpl: CocktailListRepositoryImpl


    @Before
    fun setUp() {
        mockCocktailListService = mockk<CocktailListService>()
        cocktailListRepositoryImpl = CocktailListRepositoryImpl(mockCocktailListService)
    }


    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return flow resource cocktail list model`() =
        runTest {

            // GIVEN
            val cocktailListModel =
                TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))

            coEvery { mockCocktailListService.fetchCocktailList() } returns flowOf(cocktailListModel)

            // Act
            val result = mutableListOf<Resources<List<CocktailListModel>>>()

            cocktailListRepositoryImpl.fetchCocktailList().collect { result.add(it) }

            // Assert
            assertEquals(cocktailListModel, result.first())
        }

    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure network exception`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailListService.fetchCocktailList() } returns flowOf(Resources.Failure(expectedException))

        val result = cocktailListRepositoryImpl.fetchCocktailList().first()

        assertEquals(expectedException.message, (result as Resources.Failure).exception.message)
    }


    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure api exception`() = runTest {

    val exception = HttpException(Response.error<ResponseBody>(500, internalServerError.toResponseBody()))

        coEvery { mockCocktailListService.fetchCocktailList() } returns flowOf(Resources.Failure(exception))

        val result = cocktailListRepositoryImpl.fetchCocktailList().first()

        assertEquals(exception.message, (result as Resources.Failure).exception.message)
    }

    @Test
    fun `GIVEN cocktail list service WHEN fetchCocktailList is called THEN return resource failure data exception`() = runTest {

        val exception = DataException(mapperError)

        coEvery { mockCocktailListService.fetchCocktailList() } returns flowOf(Resources.Failure(exception))

        val result = cocktailListRepositoryImpl.fetchCocktailList().first()

        assertEquals(exception.message, (result as Resources.Failure).exception.message)
    }

}