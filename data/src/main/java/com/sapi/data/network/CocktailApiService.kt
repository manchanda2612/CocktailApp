package com.sapi.data.network

import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.data.model.cocktaillist.CocktailListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Neeraj Manchanda
 * An interface defines the endpoints for making API calls to retrieve a list of cocktail and cocktail details.
 */
interface CocktailApiService {

    @GET("search.php")
    suspend fun getCocktailList(
        @Query("f") query: String = "m"
    ): Response<CocktailListResponse>

    @GET("lookup.php")
    suspend fun getCocktailDetail(
        @Query("i") cocktailId: String
    ): Response<CocktailDetailResponse>

}