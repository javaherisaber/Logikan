package ir.logicbase.core.ui.databinding.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.INVALID_POSITION
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

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

/**
 * set value to spinner (one way)
 */
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

/**
 * Notify two way data binding when to set new value for `selectedValue` attribute
 */
@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setSelectedValueInverseBindingListener(listener: InverseBindingListener?) {
    setInverseBindingListener(listener)
}

/**
 * getter method for two way data binding
 */
@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? = selectedItem

/**
 * setter method for two way data binding
 */
@BindingAdapter("selectedIndex")
fun Spinner.setSelectedIndex(index: Int?) {
    index?.let {
        setSelection(index)
    }
}

/**
 * Notify two way data binding when to set new value for `selectedIndex` attribute
 */
@BindingAdapter("selectedIndexAttrChanged")
fun Spinner.setSelectedIndexInverseBindingListener(listener: InverseBindingListener?) {
    setInverseBindingListener(listener)
}

/**
 * getter method for two way data binding
 */
@InverseBindingAdapter(attribute = "selectedIndex", event = "selectedIndexAttrChanged")
fun Spinner.getSelectedIndex(): Int? = if (selectedItemPosition == INVALID_POSITION) {
    null
} else {
    selectedItemPosition
}

@Suppress("UNCHECKED_CAST")
private fun Spinner.setSpinnerValue(value: Any?) {
    if (adapter != null) {
        val position = (adapter as ArrayAdapter<Any>).getPosition(value)
        setSelection(position, false)
        tag = position // use this tag to prevent infinite loop
    }
}

private fun Spinner.setInverseBindingListener(listener: InverseBindingListener?) {
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
                    listener.onChange()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

interface ItemSelectedListener {
    fun onItemSelected(item: Any, position: Int)
}