package com.neeraj.data.mapper

import com.neeraj.common.network.Resources
import com.neeraj.data.model.CocktailListResponseModel
import com.neeraj.domain.model.CocktailListModel
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * This class is responsible for mapping data from response models (e.g., CocktailListResponseModel, CocktailDetailResponseModel) to application's domain models (CocktailListModel, CocktailDetailModel).
 * It basically transforming network data into a format that's more suitable for application's needs.
 */
class CocktailListMapper @Inject constructor() {

    fun getCocktailList(cocktailListResponseModel: CocktailListResponseModel): Resources<List<CocktailListModel>> =
        Resources.Success(cocktailListResponseModel.drinks.map {
            CocktailListModel(it.idDrink, it.strDrink, it.strAlcoholic,it.strCategory, it.strDrinkThumb
            )
        })
}