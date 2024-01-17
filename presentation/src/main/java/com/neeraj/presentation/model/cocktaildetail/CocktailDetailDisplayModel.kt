package com.neeraj.presentation.model.cocktaildetail

/**
 * @author Neeraj Manchanda
 * Data class representing the display model for a cocktail detail.
 */
data class CocktailDetailDisplayModel(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String,
    val cocktailInstructions  : String,
    val cocktailModifiedDate  : String
)
