package com.neeraj.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neeraj.domain.usecases.CocktailListUseCase
/*
import com.neeraj.common.Resources
*/
/*import com.neeraj.domain.model.cocktailsListModel
import com.neeraj.domain.usecases.GetcocktailListUseCase*/
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Neeraj Manchanda
 * It is a ViewModel that manages the logic for fetching a list of cocktails using the GetcocktailListUseCase.
 * It uses state management approach to update the UI as the data changes.
 */
@HiltViewModel
class cocktailListViewModel @Inject constructor(private val getcocktailListUseCase: CocktailListUseCase) : ViewModel() {

   /* private val cocktailListResponse = MutableStateFlow<Resources<List<cocktailsListModel>>>(
        Resources.Loading)
    val cocktailListViewModel : StateFlow<Resources<List<cocktailsListModel>>> get() = cocktailListResponse

    init {
        getcocktailList()
    }


    private fun getcocktailList() {
        viewModelScope.launch {
            cocktailListResponse.value = getcocktailListUseCase()
        }
    }*/

}