package ir.logicfan.core.ui.databinding.adapter

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import ir.logicfan.core.ui.entity.Gender

object SpinnerBindingAdapters {
    /**
     * NOTICE: consumer needs to create a string array for spinner with values ordered defined in [Gender] enum
     */
    @BindingAdapter("genderWithLabel")
    @JvmStatic
    fun genderWithLabel(spinner: Spinner, label: String?) {
        label?.let {
            val genderType = Gender.getGender(it)
            setSpinnerSelectionWithGender(spinner, genderType)
        }
    }

    /**
     * NOTICE: consumer needs to create a string array for spinner with values ordered defined in [Gender] enum
     */
    @BindingAdapter("genderWithIndex")
    @JvmStatic
    fun genderWithIndex(spinner: Spinner, index: Int?) {
        index?.let {
            val genderType = Gender.getGender(it)
            setSpinnerSelectionWithGender(spinner, genderType)
        }
    }

    private fun setSpinnerSelectionWithGender(spinner: Spinner, gender: Gender) {
        when (gender) {
            Gender.MALE -> spinner.setSelection(gender.index)
            Gender.FEMALE -> spinner.setSelection(gender.index)
            Gender.OTHER -> spinner.setSelection(gender.index)
        }
    }
}