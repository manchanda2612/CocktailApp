package com.neeraj.presentation.mapper

import android.content.Context
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.presentation.constant.cocktailDetailDisplayModel
import com.neeraj.presentation.constant.cocktailDetailModel
import com.neeraj.presentation.mapper.cocktaildetail.CocktailDetailDisplayMapper
import com.neeraj.presentation.model.cocktaildetail.CocktailDetailDisplayModel
import com.neeraj.presentation.util.TestUtils
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailDetailDisplayMapperTest {

    private lateinit var cocktailDetailDisplayMapper : CocktailDetailDisplayMapper
    private val context = mockk<Context>()
    @Before
    fun setUp() {
        cocktailDetailDisplayMapper = CocktailDetailDisplayMapper(context)
    }

   /* @Test
    fun `GIVEN cocktail list model WHEN getCocktailList is called THEN return cocktail list display model`() {

        // GIVEN
        val cocktailDetailDisplayModel = TestUtils.convertJsonToModel<CocktailDetailDisplayModel>(
            TestUtils.getJsonFile(cocktailDetailDisplayModel))

        val cocktailDetailModel = TestUtils.convertJsonToModel<CocktailDetailModel>(TestUtils.getJsonFile(
            cocktailDetailModel))

        // WHEN
        val result = cocktailDetailDisplayMapper.getCocktailDetail(cocktailDetailModel)

        //Then
        assertEquals(cocktailDetailDisplayModel, result)

    }*/
}