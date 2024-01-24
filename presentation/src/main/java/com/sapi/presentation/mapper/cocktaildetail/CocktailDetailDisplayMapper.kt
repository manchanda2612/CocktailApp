package com.sapi.presentation.mapper.cocktaildetail

import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.presentation.constant.MapperConstant
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import com.sapi.presentation.utils.MapperUtil.Companion.mapStringWithHeading
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Mapper class responsible for mapping [CocktailDetail] to [CocktailDetailDisplay].
 */
class CocktailDetailDisplayMapper @Inject constructor() {
    fun getCocktailDetail(cocktailDetailModel: CocktailDetail): CocktailDetailDisplay =
        cocktailDetailModel.run {
            CocktailDetailDisplay(cocktailId,
                cocktailName,
                mapStringWithHeading(MapperConstant.DrinkType, isAlcoholic),
                mapStringWithHeading(MapperConstant.Category, cocktailCategory),
                cocktailImage,
                mapStringWithHeading(MapperConstant.Instructions, cocktailInstruction),
                mapStringWithHeading(MapperConstant.Modified, cocktailModifiedDate),
                )
        }
}