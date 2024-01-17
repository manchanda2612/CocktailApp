package com.neeraj.data.model.cocktaillist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Neeraj Manchanda
 * Data class representing the response model for a list of cocktails.
 * This class is used to deserialize JSON responses from the API, providing a structured
 * representation of a list of cocktails.
 *
 * @property drinks List of [Drink] objects, each containing basic details of a specific cocktail.
 */
data class CocktailListResponseModel(
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
        val strInstructions: String
    )
}