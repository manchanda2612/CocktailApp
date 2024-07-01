package com.sapi.presentation.screens.cocktaillist


import androidx.lifecycle.viewModelScope
import com.sapi.common.network.Resources
import com.sapi.domain.usecases.cocktaillist.CocktailListUseCases
import com.sapi.presentation.base.BaseViewModel
import com.sapi.presentation.base.ViewIntent
import com.sapi.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val cocktailListUseCase: CocktailListUseCases,
    private val cocktailListDisplayMapper: CocktailListDisplayMapper
) : BaseViewModel<CocktailListViewState, CocktailListViewIntent, CocktailListSideEffect>() {

    init {
        sendIntent(CocktailListViewIntent.FetchCocktailListView)
    }

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
            val result = cocktailListUseCase()
            when (result) {
                Resources.Loading -> {
                    state.emit(CocktailListViewState.Loading)
                }

                is Resources.Success -> {
                     state.emit(
                        CocktailListViewState.Success(
                            cocktailListDisplayMapper.getCocktailList(
                                result.data
                            )
                        )
                    )
                }

                is Resources.Failure -> {
                    state.emit(CocktailListViewState.Error(result.exception.message.toString()))
                }
            }
        }
    }

    private fun navigateToDetailScreen(id: String) {
        viewModelScope.launch {
            sideEffect.emit(CocktailListSideEffect.NavigateToDetails(id))
        }
    }
}
