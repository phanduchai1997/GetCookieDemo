package com.madgicx.ai_digital_marketing.utils

import android.util.Patterns

object ValidateUtils {

    private const val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun removeFirstLastChar(s: String?, count: Int): String? {
        return if (s == null || s.isEmpty()) null else s.substring(0, s.length - count).substring(
                count
        )
    }

    fun randomAlphaNumeric(count: Int): String {
        var count = count
        val builder = StringBuilder()
        while (count-- != 0) {
            val character = (Math.random() * ALPHA_NUMERIC_STRING.length).toInt()
            builder.append(ALPHA_NUMERIC_STRING[character])
        }
        return builder.toString()
    }

    fun checkStringEmpty(content: String): Boolean {
        return content.isEmpty()
    }

    fun checkString(strData: String?): String {
        var strData = strData
        if (strData == null) {
            strData = ""
        }
        if (strData == "" || strData.isEmpty() || strData == "null") {
            strData = ""
        }

        return strData
    }

    fun isNumeric(str: String): Boolean {
        try {
            val d = java.lang.Double.parseDouble(str)
        } catch (nfe: NumberFormatException) {
            return false
        }

        return true
    }

    fun stringToInt(strData: String?): Int {
        var strData = strData
        if (strData == null) {
            strData = "0"
        }
        if (strData == "" || strData.isEmpty() || strData == "null") {
            strData = "0"
        }

        return Integer.valueOf(strData)
    }

    fun telCheck(str: String): Boolean? {
        return str.matches("^0\\d{1,4}-\\d{1,4}-\\d{4}$".toRegex())
    }

    fun telCheck(target: CharSequence?): Boolean? {
        return target?.matches("^0\\d{1,4}-\\d{1,4}-\\d{4}$".toRegex())
    }

    fun isValidPhoneNumber(target: CharSequence?): Boolean {
        return if (target == null || target.length < 6 || target.length > 13) {
            false
        } else {
            Patterns.PHONE.matcher(target).matches()
        }
    }

    fun mailCheck(str: String): Boolean? {
        return str.matches("[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+".toRegex())
    }
}