package com.neeraj.domain.model.cocktaillist


/**
 * @author Neeraj Manchanda
 *Data class representing basic information of a cocktail in a list.
 */
data class CocktailListModel(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String
)
