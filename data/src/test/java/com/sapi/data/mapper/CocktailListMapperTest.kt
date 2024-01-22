package com.sapi.data.mapper

import com.sapi.data.constant.cocktailListJson
import com.sapi.data.constant.cocktailListResponseJson
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

        val cocktailListResponseModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(
            cocktailListResponseJson), CocktailListResponse::class.java)
        val cocktailListModel = TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(
            cocktailListJson))

        val result = cocktailListMapper.getCocktailList(cocktailListResponseModel)

        assertEquals(cocktailListModel, result)
    }
}