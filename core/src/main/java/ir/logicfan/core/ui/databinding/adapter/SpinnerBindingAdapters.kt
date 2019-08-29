package ir.logicfan.core.ui.databinding.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import ir.logicfan.core.ui.entity.Gender

/**
 * NOTICE: consumer needs to create a string array for spinner with values ordered defined in [Gender] enum
 */
@BindingAdapter("genderWithLabel")
fun Spinner.setSelectionWithGenderLabel(label: String?) {
    label?.let {
        val genderType = Gender.getGender(it)
        setSpinnerSelectionWithGender(this, genderType)
    }
}

/**
 * NOTICE: consumer needs to create a string array for spinner with values ordered defined in [Gender] enum
 */
@BindingAdapter("genderWithIndex")
fun Spinner.setSelectionWithGenderIndex(index: Int?) {
    index?.let {
        val genderType = Gender.getGender(it)
        setSpinnerSelectionWithGender(this, genderType)
    }
}

private fun setSpinnerSelectionWithGender(spinner: Spinner, gender: Gender) {
    when (gender) {
        Gender.MALE -> spinner.setSelection(gender.index)
        Gender.FEMALE -> spinner.setSelection(gender.index)
        Gender.OTHER -> spinner.setSelection(gender.index)
    }
}

@BindingAdapter("entries")
fun Spinner.setEntries(entries: List<Any>?) {
    entries?.let {
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, entries)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.adapter = arrayAdapter
    }
}

@BindingAdapter("onItemSelected")
fun Spinner.setItemSelectedListener(listener: ItemSelectedListener?) {
    if (listener == null) {
        onItemSelectedListener = null
    } else {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // using tag to prevent infinite loop
                if (tag != position) {
                    listener.onItemSelected(parent.getItemAtPosition(position), position)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

@BindingAdapter("newValue")
fun Spinner.setNewValue(newValue: Any?) {
    setSpinnerValue(newValue)
}

/**
 * setter method for two way data binding
 */
@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(selectedValue: Any?) {
    setSpinnerValue(selectedValue)
}

@Suppress("UNCHECKED_CAST")
private fun Spinner.setSpinnerValue(value: Any?) {
    if (adapter != null) {
        val position = (adapter as ArrayAdapter<Any>).getPosition(value)
        setSelection(position, false)
        tag = position // use this tag to prevent infinite loop
    }
}

/**
 * Notify two way data binding when to set new value for `selectedValue` attribute
 */
@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(listener: InverseBindingListener?) {
    if (listener == null) {
        onItemSelectedListener = null
    } else {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // using tag to prevent infinite loop
                if (tag != position) {
                    listener.onChange()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

/**
 * getter method for two way data binding
 */
@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? = selectedItem

interface ItemSelectedListener {
    fun onItemSelected(item: Any, position: Int)
}