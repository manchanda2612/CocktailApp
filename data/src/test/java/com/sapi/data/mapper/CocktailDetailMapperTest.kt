package com.sapi.data.mapper

import com.sapi.data.constant.cocktailDetailJson
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.model.cocktaildetail.CocktailDetailResponse
import com.sapi.data.util.TestUtils
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailDetailMapperTest {

    private lateinit var cocktailDetailMapper : CocktailDetailMapper
    private val cocktailDetail: CocktailDetail = mockk<CocktailDetail>()
    private val cocktailDetailResponse: CocktailDetailResponse = mockk<CocktailDetailResponse>()

    @Before
    fun setUp() {
        cocktailDetailMapper = CocktailDetailMapper()
    }

    @Test
    fun `GIVEN cocktail detail response model WHEN getCocktailDetail is called THEN return resource cocktail detail model`() {

        val cocktailDetailModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(cocktailDetailJson), cocktailDetail::class.java)

        val result = cocktailDetailMapper.getCocktailDetail(cocktailDetailResponse)

        assertEquals(cocktailDetail, result)
    }
}