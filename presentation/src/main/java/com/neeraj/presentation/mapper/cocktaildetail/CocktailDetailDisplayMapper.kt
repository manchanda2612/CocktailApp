package com.neeraj.presentation.mapper.cocktaildetail

import android.content.Context
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.presentation.R
import com.neeraj.presentation.model.cocktaildetail.CocktailDetailDisplayModel
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Mapper class responsible for mapping [CocktailDetailModel] to [CocktailDetailDisplayModel].
 * @param applicationContext The [Context] used for obtaining localized string resources.
 */
class CocktailDetailDisplayMapper @Inject constructor(private val applicationContext : Context) {
    fun getCocktailDetail(cocktailDetailModel: CocktailDetailModel): CocktailDetailDisplayModel =
        cocktailDetailModel.run {
            CocktailDetailDisplayModel(cocktailId,
                cocktailName,
                applicationContext.getString(R.string.drink, isAlcoholic),
                applicationContext.getString(R.string.category, cocktailCategory),
                cocktailImage,
                applicationContext.getString(R.string.instructions, cocktailInstruction),
                applicationContext.getString(R.string.modified_date, cocktailModifiedDate)
                )
        }
}