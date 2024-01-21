package com.sapi.presentation.screens.cocktaillist

import com.sapi.presentation.base.SideEffect
import com.sapi.presentation.base.ViewIntent
import com.sapi.presentation.base.ViewState
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay

sealed interface CocktailListViewState : ViewState {
    object Loading : CocktailListViewState
    data class Success(val data: List<CocktailListDisplay>) : CocktailListViewState
    data class Error(val error: String) : CocktailListViewState
}

sealed interface CocktailListViewIntent : ViewIntent {
    object FetchCocktailListView : CocktailListViewIntent
    class OnViewCocktailItemClick(val id: Int) : CocktailListViewIntent
}

sealed interface CocktailListSideEffect : SideEffect {
    class NavigateToDetails(val id: Int) : CocktailListSideEffect
}
