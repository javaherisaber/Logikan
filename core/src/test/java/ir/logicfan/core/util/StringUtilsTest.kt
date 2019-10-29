package ir.logicfan.core.util

import org.junit.Assert
import org.junit.Test

class StringUtilsTest {

    @Test
    fun numberToSeparatedThousands() {
        Assert.assertEquals(StringUtils.numberToSeparatedThousands(1200000), "1,200,000")
    }
}