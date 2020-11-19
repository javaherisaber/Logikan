package ir.logicbase.core.util

object StringUtils {

    /**
     * Build fullName by concatenating first name and last name
     */
    fun fullName(firstName: String?, lastName: String?): String? = when {
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> "$firstName $lastName"
        else -> null
    }
}