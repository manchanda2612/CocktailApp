package com.neeraj.data.model.cocktaildetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CocktailDetailResponseModel(
    @SerializedName("drinks")
    @Expose
    val drinks: List<Drink>
) {
    data class Drink(
        @SerializedName("idDrink")
        @Expose
        val idDrink: String,
        @SerializedName("strAlcoholic")
        @Expose
        val strAlcoholic: String,
        @SerializedName("strCategory")
        @Expose
        val strCategory: String,
        @SerializedName("strDrink")
        @Expose
        val strDrink: String,
        @SerializedName("strDrinkThumb")
        @Expose
        val strDrinkThumb: String,
        @SerializedName("strGlass")
        @Expose
        val strGlass: String,
        @SerializedName("strInstructions")
        @Expose
        val strInstructions: String,
        @SerializedName("dateModified")
        @Expose
        val dateModified: String
    )
}
