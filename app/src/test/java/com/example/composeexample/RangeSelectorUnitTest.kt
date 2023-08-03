package com.example.composeexample

import com.example.composeexample.components.RangeSelectorDelegate
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RangeSelectorUnitTest {

    @Test
    fun `WHEN move top value lower than the Bottom value EXPECT the top value to be bottom + 1`() {

        val range = RangeSelectorDelegate(10,30)
        range.moveTop(0)
        assertThat(range.currentTop, `is`( 11))
    }

    @Test
    fun `WHEN move bottom value higher than the Top value EXPECT the bottom value to be top - 1`() {

        val range = RangeSelectorDelegate(10,30)
        range.moveBottom(40)
        assertThat(range.currentBotton, `is`( 29))
    }

}