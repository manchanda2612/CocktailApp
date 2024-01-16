package com.neeraj.common.constant


/**
 * @author Neeraj Manchanda
 * Utility class for storing common constants used throughout the application.
 * This class contains static members within a companion object, making them easily
 * accessible without the need for class instantiation.
 */
class CommonConstant {

    /**
     * Companion object containing common string constants used in the application.
     */
    companion object {
        /**
         * Default error message to be displayed when an unexpected error occurs.
         */
        const val ErrorMessage = "Something went wrong"

        /**
         * Error message indicating a lack of internet connection.
         * To be displayed when the application requires internet access.
         */
        const val InternetErrorMessage = "Please check your internet connection."
    }
}
