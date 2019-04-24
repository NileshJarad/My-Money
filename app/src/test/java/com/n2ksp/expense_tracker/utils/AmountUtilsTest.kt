package com.n2ksp.expense_tracker.utils

import org.junit.Assert
import org.junit.Test

class AmountUtilsTest {
    @Test
    fun textOnlyPeriodConversion() {
        Assert.assertEquals("0.0", AmountUtils.getOnlyFloatValue(".").toString())
    }

    @Test
    fun checkConversion() {
        Assert.assertEquals("6.0", AmountUtils.getAmountFormatted(666.02f))
    }

    @Test
    fun textNumberPlusPeriodOnly() {
        Assert.assertEquals("6.0", "6.".toFloat().toString())
//        Assert.assertEquals("6.0", AmountUtils.getOnlyFloatValue("6.").toString())
    }
}