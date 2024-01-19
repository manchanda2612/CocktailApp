package com.neeraj.domain.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.domain.model.cocktaillist.CocktailListModel
/*
import retrofit2.Response
*/
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringWriter


/**
 * @author Neeraj Manchanda
 * This class contains utility functions for reading JSON from a resource file and parsing it into various data types,
 * including lists of CocktailListModel and CocktailDetailModel. It also includes a generic function for parsing JSON into a Response object.
 */
class TestUtils {

    companion object {

        private val gson = Gson()

        fun getJsonFile(fileName: String): String {
            val inputStream =
                this::class.java.classLoader?.getResourceAsStream(fileName)
            return getStringFromInputStream(inputStream)
        }

        @Throws(IOException::class)
        private fun getStringFromInputStream(stream: InputStream?): String {
            var n = 0
            val buffer = CharArray(1024 * 4)
            val reader = InputStreamReader(stream, "UTF8")
            val writer = StringWriter()
            while (-1 != reader.read(buffer).also { n = it }) writer.write(buffer, 0, n)
            return writer.toString()
        }

         fun parseJSONToCocktailList(jsonString: String): Resources<List<CocktailListModel>> {
                    val listType = object : TypeToken<List<CocktailListModel>>() {}.type
                    val cocktailList = gson.fromJson<List<CocktailListModel>>(jsonString, listType)
                    return Resources.Success(cocktailList)
        }

        fun parseJSONToCocktailDetail(jsonString: String): Resources<CocktailDetailModel> {
            val listType = object : TypeToken<CocktailDetailModel>() {}.type
            val cocktailDetail = gson.fromJson<CocktailDetailModel>(jsonString, listType)
            return Resources.Success(cocktailDetail)
        }

    }
}

