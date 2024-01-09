package com.neeraj.presentation.mapper

import android.content.Context
import com.neeraj.domain.model.CocktailListModel
import com.neeraj.presentation.R
import com.neeraj.presentation.model.CocktailListDisplayModel
import javax.inject.Inject

class CocktailListDisplayMapper @Inject constructor(private val applicationContext : Context) {

    fun getCocktailList(cocktailListModel: List<CocktailListModel>): List<CocktailListDisplayModel> {
        return cocktailListModel.map { cocktailItem ->
            CocktailListDisplayModel(
                cocktailItem.cocktailId,
                cocktailItem.cocktailName,
                applicationContext.getString(R.string.drink, cocktailItem.isAlcoholic),
                applicationContext.getString(R.string.category, cocktailItem.cocktailCategory),
                cocktailItem.cocktailImage
            )
        }
    }
}