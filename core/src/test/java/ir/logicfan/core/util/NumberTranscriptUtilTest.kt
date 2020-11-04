package ir.logicfan.core.util

import org.junit.Assert.assertEquals
import org.junit.Test

class NumberTranscriptUtilTest {

    @Test
    fun transcriptNumber_multipleBase() {
        val transcript = "هفت میلیون و پانصد هزار و دویست و بیست و دو"
        val number = 7500222L
        assertEquals(transcript, NumberTranscriptUtil.transcriptNumber(number))
    }

    @Test
    fun transcriptNumber_oneBase() {
        val transcript = "هفت میلیون"
        val number = 7000000L
        assertEquals(transcript, NumberTranscriptUtil.transcriptNumber(number))
    }

    @Test
    fun transcriptNumber_belowTen() {
        val transcript = "هشت"
        val number = 8L
        assertEquals(transcript, NumberTranscriptUtil.transcriptNumber(number))
    }

    @Test
    fun transcriptNumber_belowHundred() {
        val transcript = "بیست و شش"
        val number = 26L
        assertEquals(transcript, NumberTranscriptUtil.transcriptNumber(number))
    }
}