package com.sapi.presentation.mapper.cocktaildetail

import android.content.Context
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.presentation.R
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Mapper class responsible for mapping [CocktailDetail] to [CocktailDetailDisplay].
 * @param applicationContext The [Context] used for obtaining localized string resources.
 */
class CocktailDetailDisplayMapper @Inject constructor(private val applicationContext : Context) {
    fun getCocktailDetail(cocktailDetailModel: CocktailDetail): CocktailDetailDisplay =
        cocktailDetailModel.run {
            CocktailDetailDisplay(cocktailId,
                cocktailName,
                applicationContext.getString(R.string.drink, isAlcoholic),
                applicationContext.getString(R.string.category, cocktailCategory),
                cocktailImage,
                applicationContext.getString(R.string.instructions, cocktailInstruction),
                applicationContext.getString(R.string.modified_date, cocktailModifiedDate)
                )
        }
}