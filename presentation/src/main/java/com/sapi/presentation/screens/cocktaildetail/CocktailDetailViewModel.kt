package com.sapi.presentation.screens.cocktaildetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.domain.usecases.cocktaildetail.CocktailDetailUseCases
import com.sapi.presentation.base.BaseViewModel
import com.sapi.presentation.base.ViewIntent
import com.sapi.presentation.constant.UiConstants
import com.sapi.presentation.mapper.cocktaildetail.CocktailDetailDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val cocktailDetailUseCases: CocktailDetailUseCases,
    private val cocktailDetailDisplayMapper : CocktailDetailDisplayMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CocktailDetailViewState, CocktailDetailViewIntent, CocktailDetailSideEffect>() {


    init {
        val id = savedStateHandle.get<String>(UiConstants.cocktailId)
        sendIntent(CocktailDetailViewIntent.FetchCocktailDetail(id ?: "0"))
    }


    override fun sendIntent(intent: ViewIntent) {
        when(intent) {
            is CocktailDetailViewIntent.FetchCocktailDetail -> {
                fetchCocktailDetail(intent.cocktailId)
            }
        }
    }
    override fun createInitialState(): CocktailDetailViewState = CocktailDetailViewState.Loading

    private fun fetchCocktailDetail(cocktailId : String) {
        viewModelScope.launch {
            viewModelScope.launch {
                when (cocktailDetailUseCases.invoke(cocktailId)) {
                  Resources.Loading -> {
                        state.emit(CocktailDetailViewState.Loading)
                    }

                    is Resources.Success -> {
                        state.emit(
                            CocktailDetailViewState.Success(
                                cocktailDetailDisplayMapper.getCocktailDetail((cocktailDetailUseCases.invoke(cocktailId) as Resources.Success<CocktailDetail>).data)
                            )
                        )
                    }

                    is Resources.Failure -> {
                        state.emit(CocktailDetailViewState.Failure(((cocktailDetailUseCases.invoke(cocktailId) as Resources.Failure).exception.message.toString())))
                    }
                }
            }
        }
    }
}