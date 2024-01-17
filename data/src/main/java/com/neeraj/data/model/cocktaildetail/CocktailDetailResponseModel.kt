package com.neeraj.data.model.cocktaildetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Neeraj Manchanda
 * Data class representing the response model for a detailed cocktail information.
 * This class is used to deserialize JSON responses from the API, providing a structured
 * representation of the detailed information for a cocktail.
 *
 * @property drinks List of [Drink] objects, each containing details of a specific cocktail.
 */
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
