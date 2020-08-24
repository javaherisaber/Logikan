package ir.logicfan.core.ui.entity

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import ir.logicfan.core.R

enum class Gender(val code: Int, val label: String, val index: Int, @StringRes val localizedLabel: Int) {
    MALE(1, "male", 0, R.string.core_all_male),
    FEMALE(2, "female", 1, R.string.core_all_female),
    OTHER(0, "other", -1, R.string.core_all_other);

    companion object {
        @JvmStatic
        fun iranLegalValues(): List<Gender> = values().filter { item -> item.name != OTHER.name }

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
        fun getGenderWithLocalizedLabel(label: String, context: Context): Gender {
            val allGenders = values()
            for (item in allGenders) {
                if (context.getString(item.localizedLabel) == label) {
                    // found a gender with given label
                    return item
                }
            }
            throw IllegalArgumentException()
        }

        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun getLocalizedLabelWithLabel(label: String?, context: Context): String {
            val allGenders = values()
            if (label != null) {
                for (item in allGenders) {
                    if (item.label == label) {
                        // found a gender with given label
                        return context.getString(item.localizedLabel)
                    }
                }
            } else {
                return ""
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