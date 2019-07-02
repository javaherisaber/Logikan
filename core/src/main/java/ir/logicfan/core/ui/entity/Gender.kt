package ir.logicfan.core.ui.entity

enum class Gender(val index: Int, val label: String) {
    OTHER(0, "other"),
    MALE(1, "male"),
    FEMALE(2, "female");

    companion object {
        @JvmStatic
        fun getGender(label: String): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (item.label == label) {
                    // found a gender with given label
                    return item
                }
            }
            return OTHER
        }

        @JvmStatic
        fun getGender(index: Int): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (item.index == index) {
                    // found a gender with given index
                    return item
                }
            }
            return OTHER
        }
    }
}