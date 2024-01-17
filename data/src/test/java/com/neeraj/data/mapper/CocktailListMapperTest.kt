package com.neeraj.data.mapper

import com.neeraj.data.constant.cocktailListModel
import com.neeraj.data.constant.cocktailListResponseModel
import com.neeraj.data.mapper.cocktaillist.CocktailListMapper
import com.neeraj.data.model.cocktaillist.CocktailListResponseModel
import com.neeraj.data.util.TestUtils
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
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
        val cocktailListResponseModel = TestUtils.convertJsonToModel(TestUtils.getJsonFile(cocktailListResponseModel), CocktailListResponseModel::class.java)
        val cocktailListModel = TestUtils.parseJSONToCocktailList(TestUtils.getJsonFile(cocktailListModel))

        // WHEN
        val result = cocktailListMapper.getCocktailList(cocktailListResponseModel)

        //Then
        assertEquals(cocktailListModel, result)

    }
}