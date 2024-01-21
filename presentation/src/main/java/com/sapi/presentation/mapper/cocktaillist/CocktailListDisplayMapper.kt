package com.sapi.presentation.mapper.cocktaillist

import android.content.Context
import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.presentation.R
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Mapper class responsible for mapping a list of [CocktailList] to a list of [CocktailListDisplay].
 * @param applicationContext The [Context] used for obtaining localized string resources.
 */
class CocktailListDisplayMapper @Inject constructor(private val applicationContext : Context) {

    fun getCocktailList(cocktailListModel: List<CocktailList>): List<CocktailListDisplay> {
        return cocktailListModel.map { cocktailItem ->
            CocktailListDisplay(
                cocktailItem.cocktailId,
                cocktailItem.cocktailName,
                applicationContext.getString(R.string.drink, cocktailItem.isAlcoholic),
                cocktailItem.cocktailImage
            )
        }
    }
}