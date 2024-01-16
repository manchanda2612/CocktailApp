package com.neeraj.presentation.screens.cocktaildetail

import androidx.lifecycle.viewModelScope
import com.neeraj.common.network.Resources
import com.neeraj.domain.usecases.cocktaildetail.CocktailDetailUseCases
import com.neeraj.presentation.base.BaseViewModel
import com.neeraj.presentation.base.ViewIntent
import com.neeraj.presentation.mapper.cocktaildetail.CocktailDetailDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val cocktailDetailUseCases: CocktailDetailUseCases,
    private val cocktailDetailDisplayMapper : CocktailDetailDisplayMapper
) : BaseViewModel<CocktailDetailViewState, CocktailDetailViewIntent, CocktailDetailSideEffect>() {
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
            cocktailDetailUseCases(cocktailId)
                .onStart {
                    state.emit(CocktailDetailViewState.Loading)
                }
                .catch {
                    state.emit(CocktailDetailViewState.Failure(it.message.toString()))
                }
                .collect{ result ->
                    when (result) {
                        is Resources.Success -> {
                            state.emit(
                                CocktailDetailViewState.Success(
                                    cocktailDetailDisplayMapper.getCocktailDetail(result.data)
                                )
                            )
                        }

                        is Resources.Failure -> {
                            state.emit(CocktailDetailViewState.Failure(result.exception.message.toString()))
                        }

                        else -> {}
                    }
                }
        }
    }

}