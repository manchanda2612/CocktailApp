package com.sapi.domain.model.cocktaildetail

/**
 * @author Neeraj Manchanda
 * Data class representing the detailed information of a cocktail.
 */
data class CocktailDetail(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String,
    val cocktailInstruction : String,
    val cocktailModifiedDate : String
)

