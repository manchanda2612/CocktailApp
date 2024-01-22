package com.sapi.presentation.mapper

import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.presentation.constant.cocktailDetailDisplayModel
import com.sapi.presentation.constant.cocktailDetailModel
import com.sapi.presentation.mapper.cocktaildetail.CocktailDetailDisplayMapper
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import com.sapi.presentation.util.TestUtils
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailDetailDisplayMapperTest {

    private lateinit var cocktailDisplayDetailMapper : CocktailDetailDisplayMapper

    @Before
    fun setUp() {
        cocktailDisplayDetailMapper = CocktailDetailDisplayMapper()
    }

    @Test
    fun `GIVEN cocktail list model WHEN getCocktailList is called THEN return cocktail list display model`() {


        val cocktailDetailDisplay = TestUtils.convertJsonToModel<CocktailDetailDisplay>(
            TestUtils.getJsonFile(cocktailDetailDisplayModel))

        val cocktailDetailModel = TestUtils.convertJsonToModel<CocktailDetail>(TestUtils.getJsonFile(
            cocktailDetailModel))


        val result = cocktailDisplayDetailMapper.getCocktailDetail(cocktailDetailModel)


        assertEquals(cocktailDetailDisplay.cocktailId, result.cocktailId)
    }
}