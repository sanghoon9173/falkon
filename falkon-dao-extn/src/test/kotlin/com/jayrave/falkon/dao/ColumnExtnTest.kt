package com.jayrave.falkon.dao

import com.jayrave.falkon.dao.testLib.ModelForTest
import com.jayrave.falkon.dao.testLib.TableForTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ColumnExtnTest {

    @Test
    fun testExtractPropertyFrom() {
        val inputValue = "this could be anything"
        val model = ModelForTest(string = inputValue)
        val actualValue = TableForTest().string.extractPropertyFrom(model)
        assertThat(actualValue).isSameAs(inputValue)
    }
}