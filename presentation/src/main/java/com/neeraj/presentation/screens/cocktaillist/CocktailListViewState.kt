package com.neeraj.presentation.screens.cocktaillist

import com.neeraj.presentation.base.SideEffect
import com.neeraj.presentation.base.ViewIntent
import com.neeraj.presentation.base.ViewState
import com.neeraj.presentation.model.cocktaillist.CocktailListDisplayModel

sealed interface CocktailListViewState : ViewState {
    object Loading : CocktailListViewState
    data class Success(val data: List<CocktailListDisplayModel>) : CocktailListViewState
    data class Error(val error: String) : CocktailListViewState
}

sealed interface CocktailListViewIntent : ViewIntent {
    object FetchCocktailListView : CocktailListViewIntent
    class OnViewCocktailItemClick(val id: Int) : CocktailListViewIntent
}

sealed interface CocktailListSideEffect : SideEffect {
    class NavigateToDetails(val id: Int) : CocktailListSideEffect
}
