package com.sapi.presentation.mapper.cocktaillist

import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.presentation.constant.MapperConstant
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.utils.MapperUtil.Companion.mapStringWithHeading
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Mapper class responsible for mapping a list of [CocktailList] to a list of [CocktailListDisplay].
 */
class CocktailListDisplayMapper @Inject constructor() {

    fun getCocktailList(cocktailListModel: List<CocktailList>): List<CocktailListDisplay> {
        return cocktailListModel.map { cocktailItem ->
            CocktailListDisplay(
                cocktailItem.cocktailId,
                cocktailItem.cocktailName,
                mapStringWithHeading(MapperConstant.DrinkType, cocktailItem.isAlcoholic),
                cocktailItem.cocktailImage
            )
        }
    }
}