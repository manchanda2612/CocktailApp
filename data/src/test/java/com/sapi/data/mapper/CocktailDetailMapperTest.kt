package com.sapi.data.mapper

import com.sapi.data.constant.cocktailDetailModel
import com.sapi.data.constant.cocktailDetailResponseModel
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.data.util.TestUtils
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
        val cocktailDetailResponseModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(cocktailDetailResponseModel), CocktailDetailResponse::class.java)
        val cocktailDetailModel = TestUtils.parseJSONToCocktailDetail(TestUtils.getJsonFile(cocktailDetailModel))

        // WHEN
        val result = cocktailDetailMapper.getCocktailDetail(cocktailDetailResponseModel)

        //Then
        assertEquals(cocktailDetailModel, result)

    }
}