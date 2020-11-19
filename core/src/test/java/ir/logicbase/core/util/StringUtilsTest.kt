package ir.logicbase.core.util

import org.junit.Test

import org.junit.Assert.*

class StringUtilsTest {

    @Test
    fun fullName_firstNameOnly_returnFirstName() {
        assertEquals(firstName, StringUtils.fullName(firstName, null))
    }

    @Test
    fun fullName_lastNameOnly_returnLastName() {
        assertEquals(lastName, StringUtils.fullName(null, lastName))
    }

    @Test
    fun fullName_firstLast_returnBoth() {
        assertEquals("$firstName $lastName", StringUtils.fullName(firstName, lastName))
    }

    companion object {
        const val firstName = "Mahdi"
        const val lastName = "Javaheri"
    }
}