package ir.logicbase.core.ui.databinding.adapter

import android.app.TimePickerDialog
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.BindingAdapter
import at.blogc.android.views.ExpandableTextView
import java.util.*

@BindingAdapter("hideIfNullOrZero")
fun View.hideIfNullOrZero(number: Number?) {
    this.visibility = if (number == null || number.toDouble() == 0.0) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("goneIfNullOrZero")
fun View.goneIfNullOrZero(number: Number?) {
    this.visibility = if (number == null || number.toDouble() == 0.0) View.GONE else View.VISIBLE
}

/**
 * Set visibility to [View.GONE] when [isVisible] is false
 */
@BindingAdapter("android:visibility")
fun View.setVisibleGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * Set visibility to [View.INVISIBLE] when [isVisible] is false
 */
@BindingAdapter("visible")
fun View.setVisibleInVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

/**
 * Toggle expandableTextView when clicked on expand/collapse button
 */
@BindingAdapter("expandableTextViewAnchor")
fun View.expandableTextViewAnchor(anchor: ExpandableTextView?) = anchor?.let {
    this.setOnClickListener { anchor.toggle() }
}

@BindingAdapter(value = ["popupData", "onPopupItemClick"], requireAll = false)
fun View.onViewClickToShowPopup(
    popupData: Collection<PopupData>?,
    onPopupItemClickListener: OnPopupItemClickListener?
) = popupData?.let {
    this.setOnClickListener { view ->
        val popup = PopupMenu(context, view)
        for (data in popupData) {
            popup.menu.add(Menu.NONE, data.id, Menu.NONE, data.title)
        }
        popup.setOnMenuItemClickListener { menuItem ->
            val itemData = PopupData(menuItem.itemId, menuItem.title.toString())
            onPopupItemClickListener?.onPopupItemClick(itemData)
            return@setOnMenuItemClickListener false
        }
        popup.show()
    }
}

/**
 * @param popupData stringArray in strings.xml
 */
@BindingAdapter(value = ["popupData", "onPopupItemClick"], requireAll = false)
fun View.onViewClickToShowPopupWithResId(
    popupData: Array<String>?,
    onPopupItemClickListener: OnPopupItemClickListener?
) = popupData?.let {
    this.setOnClickListener { view ->
        val popup = PopupMenu(context, view)
        for ((index, data) in popupData.withIndex()) {
            popup.menu.add(Menu.NONE, index, Menu.NONE, data)
        }
        popup.setOnMenuItemClickListener { menuItem ->
            val itemData = PopupData(menuItem.itemId, menuItem.title.toString())
            onPopupItemClickListener?.onPopupItemClick(itemData)
            return@setOnMenuItemClickListener false
        }
        popup.show()
    }
}

@BindingAdapter("onTimePickListener")
fun View.onViewClickToShowTimePicker(onTimePickListener: OnTimePickListener?) {
    this.setOnClickListener {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // Show Time Picker Dialog
        val timePickerDialog = TimePickerDialog(
            this.context, { _, hour, minute ->
                onTimePickListener?.onTimePick(hour, minute)
            }, currentHour, currentMinute, true
        )
        timePickerDialog.show()
    }
}

fun interface OnTimePickListener {
    fun onTimePick(hour: Int, minute: Int)
}

fun interface OnPopupItemClickListener {
    fun onPopupItemClick(popupData: PopupData)
}

data class PopupData(val id: Int, val title: String)