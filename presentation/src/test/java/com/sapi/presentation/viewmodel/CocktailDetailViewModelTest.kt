package com.sapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.domain.usecases.cocktaildetail.CocktailDetailUseCases
import com.sapi.presentation.constant.UiConstants
import com.sapi.presentation.constant.cocktailDetailDisplayModel
import com.sapi.presentation.constant.cocktailDetailModel
import com.sapi.presentation.constant.cocktailId
import com.sapi.presentation.mapper.cocktaildetail.CocktailDetailDisplayMapper
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import com.sapi.presentation.screens.cocktaildetail.CocktailDetailViewIntent
import com.sapi.presentation.screens.cocktaildetail.CocktailDetailViewModel
import com.sapi.presentation.screens.cocktaildetail.CocktailDetailViewState
import com.sapi.presentation.util.TestUtils
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CocktailDetailViewModelTest {

    // Mock dependencies
    private val testDispatcher = UnconfinedTestDispatcher()
    private val mockCocktailDetailUseCase: CocktailDetailUseCases = mockk()
    private val mockCocktailDetailDisplayMapper: CocktailDetailDisplayMapper = mockk()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var viewModel: CocktailDetailViewModel


    @Before
    fun setup() {

        // Use TestCoroutineDispatcher for testing
        Dispatchers.setMain(testDispatcher)

        val cocktailDetail = TestUtils.convertJsonToModel<CocktailDetail>(
            TestUtils.getJsonFile(cocktailDetailModel))

        val cocktailDetailDisplay = TestUtils.convertJsonToModel<CocktailDetailDisplay>(
            TestUtils.getJsonFile(cocktailDetailDisplayModel))

        every { savedStateHandle.get<String>(UiConstants.cocktailId) } returns cocktailId

        coEvery { mockCocktailDetailUseCase(cocktailId) } answers {
            Resources.Success(cocktailDetail)
        }

        every { mockCocktailDetailDisplayMapper.getCocktailDetail(cocktailDetail) } answers {
            cocktailDetailDisplay
        }

        viewModel = CocktailDetailViewModel(mockCocktailDetailUseCase,mockCocktailDetailDisplayMapper,savedStateHandle)
    }

    @Test
    fun `GIVEN fetch cocktail detail intent WHEN fetchCocktailDetail called THEN return cocktail detail successfully`() = runTest {

        val cocktailDetailDisplay = TestUtils.convertJsonToModel<CocktailDetailDisplay>(
            TestUtils.getJsonFile(cocktailDetailDisplayModel))

        val cocktailDetail = TestUtils.convertJsonToModel<CocktailDetail>(TestUtils.getJsonFile(
            cocktailDetailModel
        ))

        coEvery { mockCocktailDetailUseCase.invoke(cocktailId) } returns Resources.Success(cocktailDetail)
        coEvery { mockCocktailDetailDisplayMapper.getCocktailDetail(cocktailDetail) } returns cocktailDetailDisplay

        viewModel.sendIntent(CocktailDetailViewIntent.FetchCocktailDetail(cocktailId))

        assertEquals(CocktailDetailViewState.Success(cocktailDetailDisplay), viewModel.viewState.value)
    }


    @Test
    fun `GIVEN fetch cocktail detail intent WHEN fetchCocktailDetail called THEN return error`() = runTest {

        val expectedException = NetworkException(CommonConstant.InternetErrorMessage)

        coEvery { mockCocktailDetailUseCase.invoke(cocktailId) } returns Resources.Failure(expectedException)

        viewModel.sendIntent(CocktailDetailViewIntent.FetchCocktailDetail(cocktailId))

        assertEquals(CocktailDetailViewState.Failure(expectedException.message.toString()), viewModel.viewState.value)
    }


    @Test
    fun `GIVEN fetch cocktail detail intent WHEN fetchCocktailDetail called THEN return loading`() = runTest {

        coEvery { mockCocktailDetailUseCase.invoke(cocktailId) } returns Resources.Loading

        viewModel.sendIntent(CocktailDetailViewIntent.FetchCocktailDetail(cocktailId))

        assertEquals(CocktailDetailViewState.Loading, viewModel.viewState.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}