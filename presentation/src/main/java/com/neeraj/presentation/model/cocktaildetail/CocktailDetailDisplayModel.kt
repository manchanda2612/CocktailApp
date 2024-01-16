package com.neeraj.presentation.model.cocktaildetail

data class CocktailDetailDisplayModel(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String,
    val cocktailInstructions  : String,
    val cocktailModifiedDate  : String
)
