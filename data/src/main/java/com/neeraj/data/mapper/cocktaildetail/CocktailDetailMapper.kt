package com.neeraj.data.mapper.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.data.model.cocktaildetail.CocktailDetailResponseModel
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * This class is responsible for mapping data from response models CocktailDetailResponseModel to application's domain models CocktailDetailModel.
 * It basically transforming network data into a format that's more suitable for application's needs.
 */
class CocktailDetailMapper @Inject constructor() {

    fun getCocktailDetail(cocktailDetailResponseModel: CocktailDetailResponseModel): Resources<CocktailDetailModel> =
        cocktailDetailResponseModel.drinks[0].run {
            return Resources.Success(
                CocktailDetailModel(
                    idDrink,
                    strDrink,
                    strAlcoholic,
                    strCategory,
                    strDrinkThumb,
                    strInstructions,
                    dateModified
                )
            )
        }
}