package com.sapi.presentation.mapper

import android.content.Context
import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.presentation.constant.cocktailListDisplayModel
import com.sapi.presentation.constant.cocktailListModel
import com.sapi.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.util.TestUtils
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailListDisplayMapperTest {

    private lateinit var cocktailDisplayListMapper : CocktailListDisplayMapper
    private val context = mockk<Context>()
    @Before
    fun setUp() {
        every { context.getString(any(), any()) } returns "Non alcoholic"
        cocktailDisplayListMapper = CocktailListDisplayMapper(context)
    }

    @Test
    fun `GIVEN cocktail list model WHEN getCocktailList is called THEN return cocktail list display model`() {

        // GIVEN
        val cocktailListDisplayModel = TestUtils.convertJsonToModel<List<CocktailListDisplay>>(
            TestUtils.getJsonFile(cocktailListDisplayModel))

        val cocktailListModel = TestUtils.convertJsonToModel<List<CocktailList>>(TestUtils.getJsonFile(
            cocktailListModel))

        // WHEN
        val result = cocktailDisplayListMapper.getCocktailList(cocktailListModel)

        //Then
        assertEquals(cocktailListDisplayModel, result)

    }
}