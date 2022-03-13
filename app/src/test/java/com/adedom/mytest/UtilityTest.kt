package com.adedom.mytest

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UtilityTest {

    @Test
    fun isGradeA_goodScore_returnTure() {
        val score: Int? = 95

        val result = Utility.isGradeA(score)

        assertEquals(true, result)
    }

    @Test
    fun isGradeA_isNull_returnFalse() {
        val score: Int? = null

        val result = Utility.isGradeA(score)

        assertEquals(false, result)
    }

    @Test
    fun isGradeA_lessThanZero_returnFalse() {
        val score: Int? = -8

        val result = Utility.isGradeA(score)

        assertEquals(false, result)
    }

    @Test
    fun isGradeA_moreThanHundred_returnFalse() {
        val score: Int? = 198

        val result = Utility.isGradeA(score)

        assertEquals(false, result)
    }

    @Test
    fun isGradeA_badGrade_returnFalse() {
        val score: Int? = 78

        val result = Utility.isGradeA(score)

        assertEquals(false, result)
    }

    @Test
    fun toPriceString_priceOverDigit_returnPrice() {
        val price: Double? = 9876.5432

        val result = Utility.toPriceString(price)

        assertEquals("9,876.54", result)
    }

    @Test
    fun toPriceString_isNull_returnUnknown() {
        val price: Double? = null

        val result = Utility.toPriceString(price)

        assertEquals("UNKNOWN", result)
    }
}