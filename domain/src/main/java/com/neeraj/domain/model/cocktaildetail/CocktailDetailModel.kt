package com.neeraj.domain.model.cocktaildetail

data class CocktailDetailModel(
    val cocktailId : String,
    val cocktailName : String,
    val isAlcoholic : String,
    val cocktailCategory : String,
    val cocktailImage : String,
    val cocktailInstruction : String,
    val cocktailModifiedDate : String
)

