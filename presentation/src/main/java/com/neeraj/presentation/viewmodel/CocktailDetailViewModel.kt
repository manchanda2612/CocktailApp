package com.neeraj.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.neeraj.domain.usecases.CocktailListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * It is a ViewModel class that handles the logic for fetching cocktail details using the GetCocktailDetailUseCase.
 * It uses state management approach to update the UI as the data changes.
 */
@HiltViewModel
class CocktailDetailViewModel @Inject constructor(private val cocktailDetailUseCase: CocktailListUseCase) : ViewModel()  {

    /*private val cocktailDetailResponse = MutableStateFlow<Resources<CocktailDetailModel>>(
        Resources.Loading)
    val cocktailDetail : StateFlow<Resources<CocktailDetailModel>>
        get() = cocktailDetailResponse


    fun getCocktailDetail(cocktailId : String) {
        viewModelScope.launch {
            cocktailDetailResponse.value = cocktailDetailUseCase(cocktailId)
        }
    }*/

}