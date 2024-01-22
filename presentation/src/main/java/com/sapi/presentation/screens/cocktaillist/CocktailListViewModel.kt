package com.sapi.presentation.screens.cocktaillist


import androidx.lifecycle.viewModelScope
import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList
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
            when (cocktailListUseCase.invoke()) {
                is Resources.Loading -> {
                    state.emit(CocktailListViewState.Loading)
                }

                is Resources.Success -> {
                    state.emit(
                        CocktailListViewState.Success(
                            cocktailListDisplayMapper.getCocktailList(
                                (cocktailListUseCase.invoke() as Resources.Success<List<CocktailList>>).data
                            )
                        )
                    )
                }

                is Resources.Failure -> {
                    state.emit(CocktailListViewState.Error((cocktailListUseCase.invoke() as Resources.Failure<List<CocktailList>>).exception.message.toString()))
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
