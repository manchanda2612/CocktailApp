package com.neeraj.presentation.mapper.cocktaillist

import android.content.Context
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.presentation.R
import com.neeraj.presentation.model.cocktaillist.CocktailListDisplayModel
import javax.inject.Inject

class CocktailListDisplayMapper @Inject constructor(private val applicationContext : Context) {

    fun getCocktailList(cocktailListModel: List<CocktailListModel>): List<CocktailListDisplayModel> {
        return cocktailListModel.map { cocktailItem ->
            CocktailListDisplayModel(
                cocktailItem.cocktailId,
                cocktailItem.cocktailName,
                applicationContext.getString(R.string.drink, cocktailItem.isAlcoholic),
                cocktailItem.cocktailImage
            )
        }
    }
}