package com.neeraj.presentation.screens.cocktaillist


import androidx.lifecycle.viewModelScope
import com.neeraj.common.network.Resources
import com.neeraj.domain.usecases.cocktaillist.CocktailListUseCases
import com.neeraj.presentation.base.BaseViewModel
import com.neeraj.presentation.base.ViewIntent
import com.neeraj.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val cocktailListUseCase: CocktailListUseCases,
    private val cocktailListDisplayMapper: CocktailListDisplayMapper
)
    : BaseViewModel<CocktailListViewState, CocktailListViewIntent, CocktailListSideEffect>() {


    override fun createInitialState(): CocktailListViewState =
        CocktailListViewState.Loading

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is CocktailListViewIntent.FetchCocktailListView -> fetchCocktailList()
            is CocktailListViewIntent.OnViewCocktailItemClick -> navigateToDetailScreen(intent.id)
        }
    }


    private fun fetchCocktailList() {
        viewModelScope.launch {
            cocktailListUseCase()
                .onStart {
                    state.emit(CocktailListViewState.Loading)
                }
                .catch {
                    state.emit(CocktailListViewState.Error(it.message.toString()))
                }.collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            state.emit(
                                CocktailListViewState.Success(
                                    cocktailListDisplayMapper.getCocktailList(result.data)
                                )
                            )
                        }

                        is Resources.Failure -> {
                            state.emit(CocktailListViewState.Error(result.exception.message.toString()))
                        }
                    }
                }
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        viewModelScope.launch {
            sideEffect.emit(CocktailListSideEffect.NavigateToDetails(id))
        }
    }
}
