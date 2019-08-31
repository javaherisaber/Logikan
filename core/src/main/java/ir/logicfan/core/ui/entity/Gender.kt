package ir.logicfan.core.ui.entity

enum class Gender(val code: Int, val label: String, val index: Int) {
    OTHER(0, "other", -1),
    MALE(1, "male", 0),
    FEMALE(2, "female", 1);

    companion object {
        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun getGenderWithLabel(label: String): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (item.label == label) {
                    // found a gender with given label
                    return item
                }
            }
            throw IllegalArgumentException()
        }

        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun getGenderWithCode(code: Int): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (item.code == code) {
                    // found a gender with given code
                    return item
                }
            }
            throw IllegalArgumentException()
        }

        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun getGenderWithIndex(index: Int): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (item.index == index) {
                    // found a gender with given index
                    return item
                }
            }
            throw IllegalArgumentException()
        }
    }
}