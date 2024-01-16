package com.neeraj.presentation.screens.cocktaildetail

import com.neeraj.presentation.base.SideEffect
import com.neeraj.presentation.base.ViewIntent
import com.neeraj.presentation.base.ViewState
import com.neeraj.presentation.model.cocktaildetail.CocktailDetailDisplayModel

sealed interface CocktailDetailViewState : ViewState {

    object Loading : CocktailDetailViewState

    data class Success(val data : CocktailDetailDisplayModel) : CocktailDetailViewState

    data class Failure(val error : String) : CocktailDetailViewState

}

sealed interface CocktailDetailViewIntent : ViewIntent {

    class FetchCocktailDetail(val cocktailId : String) : CocktailDetailViewIntent

}

sealed interface CocktailDetailSideEffect : SideEffect {}