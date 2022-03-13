package com.adedom.mytest

import java.text.DecimalFormat

object Utility {

    fun isGradeA(score: Int?): Boolean {
        if (score == null) {
            return false
        }

        if (score in 0..100) {
            return when {
                score >= 80 -> true
                else -> false
            }
        } else {
            return false
        }
    }

    fun toPriceString(price: Double?): String {
        val df = DecimalFormat("#,###.00")
        if (price != null) {
            return df.format(price)
        } else {
            return "UNKNOWN"
        }
    }
}