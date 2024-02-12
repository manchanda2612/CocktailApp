package com.sapi.presentation.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

        fun getJsonFile(fileName: String): String {
            val inputStream =
                this::class.java.classLoader?.getResourceAsStream(fileName)
            return getStringFromInputStream(inputStream)
        }

        @Throws(IOException::class)
        private fun getStringFromInputStream(stream: InputStream?): String {
            var n :Int
            val buffer = CharArray(1024 * 4)
            val reader = InputStreamReader(stream, "UTF8")
            val writer = StringWriter()
            while (-1 != reader.read(buffer).also { n = it }) writer.write(buffer, 0, n)
            return writer.toString()
        }

        inline fun <reified T> convertJsonToModel(jsonString: String): T {
            val type = object : TypeToken<T>() {}.type
            return Gson().fromJson(jsonString, type)
        }
    }
}

