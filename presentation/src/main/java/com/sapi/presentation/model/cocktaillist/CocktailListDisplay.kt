package com.sapi.presentation.model.cocktaillist

/**
 * @author Neeraj Manchanda
 * Data class representing the display model for a cocktail list.
 */
data class CocktailListDisplay(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailImage : String
)
