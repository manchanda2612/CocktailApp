package com.neeraj.presentation.mapper

import android.content.Context
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.presentation.constant.cocktailListDisplayModel
import com.neeraj.presentation.constant.cocktailListModel
import com.neeraj.presentation.mapper.cocktaillist.CocktailListDisplayMapper
import com.neeraj.presentation.model.cocktaillist.CocktailListDisplayModel
import com.neeraj.presentation.util.TestUtils
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
        val cocktailListDisplayModel = TestUtils.convertJsonToModel<List<CocktailListDisplayModel>>(
            TestUtils.getJsonFile(cocktailListDisplayModel))

        val cocktailListModel = TestUtils.convertJsonToModel<List<CocktailListModel>>(TestUtils.getJsonFile(
            cocktailListModel))

        // WHEN
        val result = cocktailDisplayListMapper.getCocktailList(cocktailListModel)

        //Then
        assertEquals(cocktailListDisplayModel, result)

    }
}