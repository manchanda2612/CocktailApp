package com.sapi.data.mapper.cocktaillist

import com.sapi.data.model.cocktaillist.CocktailListResponse
import com.sapi.domain.model.cocktaillist.CocktailList
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * This class is responsible for mapping data from response models CocktailDetailResponseModel to application's domain models CocktailListModel.
 * It basically transforming network data into a format that's more suitable for application's needs.
 */
class CocktailListMapper @Inject constructor() {

    fun getCocktailList(cocktailListResponseModel: CocktailListResponse): List<CocktailList> =
        cocktailListResponseModel.drinks.map {
            CocktailList(it.idDrink, it.strDrink, it.strAlcoholic,it.strCategory, it.strDrinkThumb)
        }
}