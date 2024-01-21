package com.sapi.data.mapper

import com.sapi.data.constant.cocktailListModel
import com.sapi.data.constant.cocktailListResponseModel
import com.sapi.data.mapper.cocktaillist.CocktailListMapper
import com.sapi.data.model.cocktaillist.CocktailListResponse
import com.sapi.data.util.TestUtils
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailListMapperTest {

    private lateinit var cocktailListMapper : CocktailListMapper

    @Before
    fun setUp() {
        cocktailListMapper = CocktailListMapper()
    }

    @Test
    fun `GIVEN cocktail list response model WHEN getCocktailList is called THEN return resource cocktail list model`() {

        // GIVEN
        val cocktailListResponseModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(cocktailListResponseModel), CocktailListResponse::class.java)
        val cocktailListModel = TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))

        // WHEN
        val result = cocktailListMapper.getCocktailList(cocktailListResponseModel)

        //Then
        assertEquals(cocktailListModel, result)

    }
}