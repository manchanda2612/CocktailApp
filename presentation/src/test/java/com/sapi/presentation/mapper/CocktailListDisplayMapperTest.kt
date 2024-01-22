package com.sapi.presentation.mapper

import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.presentation.constant.cocktailListDisplayModel
import com.sapi.presentation.constant.cocktailListModel
import com.sapi.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.util.TestUtils
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailListDisplayMapperTest {

    private lateinit var cocktailDisplayListMapper : CocktailListDisplayMapper

    @Before
    fun setUp() {
        cocktailDisplayListMapper = CocktailListDisplayMapper()
    }

    @Test
    fun `GIVEN cocktail list model WHEN getCocktailList is called THEN return cocktail list display model`() {

        val cocktailListDisplay = TestUtils.convertJsonToModel<List<CocktailListDisplay>>(
            TestUtils.getJsonFile(cocktailListDisplayModel))

        val cocktailListModel = TestUtils.convertJsonToModel<List<CocktailList>>(TestUtils.getJsonFile(
            cocktailListModel))

        val result = cocktailDisplayListMapper.getCocktailList(cocktailListModel)

        assertEquals(cocktailListDisplay[0].cocktailId, result[0].cocktailId)
    }
}