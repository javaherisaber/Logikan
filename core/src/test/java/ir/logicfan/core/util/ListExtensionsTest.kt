package ir.logicfan.core.util

import ir.logicfan.core.util.extension.createCommaSeparatedValues
import org.junit.Assert
import org.junit.Test

class ListExtensionsTest {

    @Test
    fun createCommaSeparatedValues() {
        val values = listOf(1, 3, 5, 7)
        val result = values.createCommaSeparatedValues()
        val actual = "1,3,5,7"
        Assert.assertEquals(result, actual)

        val valuesSingle = listOf(78)
        val resultSingle = valuesSingle.createCommaSeparatedValues()
        Assert.assertEquals(resultSingle, "78")
    }
}