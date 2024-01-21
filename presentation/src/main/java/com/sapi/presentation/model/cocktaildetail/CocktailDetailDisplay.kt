package com.sapi.presentation.model.cocktaildetail

/**
 * @author Neeraj Manchanda
 * Data class representing the display model for a cocktail detail.
 */
data class CocktailDetailDisplay(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String,
    val cocktailInstruction  : String,
    val cocktailModifiedDate  : String
)
