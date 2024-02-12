package com.sapi.presentation.screens

import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.DeviceConfig
import com.sapi.presentation.constant.cocktailListDisplayModel
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.screens.cocktaillist.ShowCocktailList
import com.sapi.presentation.util.TestUtils
import org.junit.Rule
import org.junit.Test

class CocktailListScreenShotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun launchComposable() {
        val cocktailListDisplay = TestUtils.convertJsonToModel<List<CocktailListDisplay>>(
            TestUtils.getJsonFile(cocktailListDisplayModel)
        )

        paparazzi.snapshot {
            ShowCocktailList(
                cocktailListDisplay,
            ) {}
        }
    }
}