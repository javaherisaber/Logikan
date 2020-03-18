/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util

import org.junit.Assert
import org.junit.Test

class StringUtilsTest {

    @Test
    fun numberToSeparatedThousands() {
        Assert.assertEquals(StringUtils.numberToSeparatedThousands(1200000), "1,200,000")
    }
}