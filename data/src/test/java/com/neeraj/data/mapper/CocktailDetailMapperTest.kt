package com.neeraj.data.mapper

import com.neeraj.data.constant.cocktailDetailModel
import com.neeraj.data.constant.cocktailDetailResponseModel
import com.neeraj.data.mapper.cocktaildetail.CocktailDetailMapper
import com.neeraj.data.model.cocktaildetail.CocktailDetailResponseModel
import com.neeraj.data.util.TestUtils
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailDetailMapperTest {

    private lateinit var cocktailDetailMapper : CocktailDetailMapper

    @Before
    fun setUp() {
        cocktailDetailMapper = CocktailDetailMapper()
    }

    @Test
    fun `GIVEN cocktail detail response model WHEN getCocktailDetail is called THEN return resource cocktail detail model`() {

        // GIVEN
        val cocktailDetailResponseModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(cocktailDetailResponseModel), CocktailDetailResponseModel::class.java)
        val cocktailDetailModel = TestUtils.parseJSONToCocktailDetail(TestUtils.getJsonFile(cocktailDetailModel))

        // WHEN
        val result = cocktailDetailMapper.getCocktailDetail(cocktailDetailResponseModel)

        //Then
        assertEquals(cocktailDetailModel, result)

    }
}