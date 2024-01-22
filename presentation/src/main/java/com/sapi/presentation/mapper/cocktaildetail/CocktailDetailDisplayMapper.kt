package com.sapi.presentation.mapper.cocktaildetail

import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.presentation.constant.MapperConstant
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
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
                MapperConstant.DrinkType.plus(isAlcoholic),
                MapperConstant.Category.plus(cocktailCategory),
                cocktailImage,
                MapperConstant.Instructions.plus(cocktailInstruction),
                MapperConstant.Modified.plus(cocktailModifiedDate)
                )
        }
}