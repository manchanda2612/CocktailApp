package com.neeraj.presentation.model.cocktaillist

/**
 * @author Neeraj Manchanda
 * Data class representing the display model for a cocktail list.
 */
data class CocktailListDisplayModel(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailImage : String
)
