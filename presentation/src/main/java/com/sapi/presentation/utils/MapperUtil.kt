package com.sapi.presentation.utils

class MapperUtil {

    companion object {

        fun mapStringWithHeading(headingString : String, isAlcoholic: String): String {
            return headingString.plus(isAlcoholic)
        }

    }

}