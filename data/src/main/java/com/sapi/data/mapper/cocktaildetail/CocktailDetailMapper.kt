package com.sapi.data.mapper.cocktaildetail

import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * This class is responsible for mapping data from response models CocktailDetailResponseModel to application's domain models CocktailDetailModel.
 * It basically transforming network data into a format that's more suitable for application's needs.
 */
class CocktailDetailMapper @Inject constructor() {

    fun getCocktailDetail(cocktailDetailResponseModel: CocktailDetailResponse):CocktailDetail =
        cocktailDetailResponseModel.drinks[0].run {
            return CocktailDetail(
                    idDrink,
                    strDrink,
                    strAlcoholic,
                    strCategory,
                    strDrinkThumb,
                    strInstructions,
                    dateModified
            )
        }
}