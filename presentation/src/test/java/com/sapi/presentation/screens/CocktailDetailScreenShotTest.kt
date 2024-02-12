package com.sapi.presentation.screens

import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.DeviceConfig
import com.sapi.presentation.constant.cocktailDetailDisplayModel
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import com.sapi.presentation.screens.cocktaildetail.ShowBookDetailScreen
import com.sapi.presentation.util.TestUtils
import org.junit.Rule
import org.junit.Test

class CocktailDetailScreenShotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun launchComposable() {
        val cocktailDetailDisplay = TestUtils.convertJsonToModel<CocktailDetailDisplay>(
            TestUtils.getJsonFile(cocktailDetailDisplayModel)
        )

        paparazzi.snapshot {
            ShowBookDetailScreen(cocktailDetailDisplay)
        }
    }
}