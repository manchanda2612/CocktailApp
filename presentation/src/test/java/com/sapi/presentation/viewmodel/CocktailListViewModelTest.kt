package com.sapi.presentation.viewmodel

import app.cash.turbine.test
import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.domain.usecases.cocktaillist.CocktailListUseCases
import com.sapi.presentation.constant.cocktailId
import com.sapi.presentation.constant.cocktailListDisplayModel
import com.sapi.presentation.constant.cocktailListModel
import com.sapi.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.screens.cocktaillist.CocktailListSideEffect
import com.sapi.presentation.screens.cocktaillist.CocktailListViewIntent
import com.sapi.presentation.screens.cocktaillist.CocktailListViewModel
import com.sapi.presentation.screens.cocktaillist.CocktailListViewState
import com.sapi.presentation.util.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CocktailListViewModelTest {

    // Mock dependencies
    private val testDispatcher = UnconfinedTestDispatcher()
    private val mockCocktailListUseCase: CocktailListUseCases = mockk()
    private val mockCocktailListDisplayMapper: CocktailListDisplayMapper = mockk()

    // Create an instance of the ViewModel with mocked dependencies
    private val viewModel = CocktailListViewModel(
        mockCocktailListUseCase,
        mockCocktailListDisplayMapper
    )

    @Before
    fun setup() {
        // Use TestCoroutineDispatcher for testing
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `GIVEN fetch cocktail list intent WHEN fetchCocktailList called THEN return cocktail list successfully`() = runTest {

        val cocktailListDisplay = TestUtils.convertJsonToModel<List<CocktailListDisplay>>(
            TestUtils.getJsonFile(cocktailListDisplayModel))

        val cocktailList = TestUtils.convertJsonToModel<List<CocktailList>>(TestUtils.getJsonFile(
            cocktailListModel
        ))

        coEvery { mockCocktailListUseCase.invoke() } returns Resources.Success(cocktailList)
        coEvery { mockCocktailListDisplayMapper.getCocktailList(cocktailList) } returns cocktailListDisplay

        viewModel.sendIntent(CocktailListViewIntent.FetchCocktailListView)

        assertEquals(CocktailListViewState.Success(cocktailListDisplay), viewModel.viewState.value)
    }


    @Test
    fun `GIVEN fetch cocktail list intent WHEN fetchCocktailList called THEN return error`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailListUseCase.invoke() } returns Resources.Failure(expectedException)

        viewModel.sendIntent(CocktailListViewIntent.FetchCocktailListView)

        assertEquals(CocktailListViewState.Error(expectedException.message.toString()), viewModel.viewState.value)
    }


    @Test
    fun `GIVEN fetch cocktail list intent WHEN fetchCocktailList called THEN return loading`() = runTest {

        coEvery { mockCocktailListUseCase.invoke() } returns Resources.Loading

        viewModel.sendIntent(CocktailListViewIntent.FetchCocktailListView)

        assertEquals(CocktailListViewState.Loading, viewModel.viewState.value)
    }

    @Test
    fun `navigate to details screen when CocktailListViewIntent OnViewCocktailItemClick intent passed`() =
        runTest {
            with(viewModel) {
                sideEffects

                sideEffects.test {
                    sendIntent(CocktailListViewIntent.OnViewCocktailItemClick(cocktailId))
                    Assert.assertTrue(awaitItem() is CocktailListSideEffect.NavigateToDetails)
                }
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}