package com.sapi.presentation.screens.cocktaildetail

import com.sapi.presentation.base.SideEffect
import com.sapi.presentation.base.ViewIntent
import com.sapi.presentation.base.ViewState
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay

sealed interface CocktailDetailViewState : ViewState {

    object Loading : CocktailDetailViewState

    data class Success(val data : CocktailDetailDisplay) : CocktailDetailViewState

    data class Failure(val error : String) : CocktailDetailViewState
}

sealed interface CocktailDetailViewIntent : ViewIntent {
    class FetchCocktailDetail(val cocktailId : String) : CocktailDetailViewIntent
}

interface CocktailDetailSideEffect : SideEffect