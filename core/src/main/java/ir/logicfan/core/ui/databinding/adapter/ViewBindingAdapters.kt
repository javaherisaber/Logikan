package ir.logicfan.core.ui.databinding.adapter

import android.app.TimePickerDialog
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.BindingAdapter
import at.blogc.android.views.ExpandableTextView
import java.util.*

@BindingAdapter("hideIfNullOrZero")
fun View.hideIfNullOrZero(number: Number?) {
    this.visibility = if (number == null || number.toDouble() == 0.0) View.GONE else View.VISIBLE
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
    popupData: Collection<Pair<String, Int>>?,
    onPopupItemClickListener: OnPopupItemClickListener?
) = popupData?.let {
    this.setOnClickListener { view ->
        val popup = PopupMenu(context, view)
        for (data in popupData) {
            popup.menu.add(data.first).titleCondensed = data.second.toString()
        }
        popup.show()
        popup.setOnMenuItemClickListener { menuItem ->
            onPopupItemClickListener?.onPopupItemClick(
                menuItem.title.toString(), menuItem.titleCondensed.toString().toInt()
            )
            return@setOnMenuItemClickListener false
        }
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
            this.context,
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                onTimePickListener?.onTimePick(hour, minute)
            }, currentHour, currentMinute, true
        )
        timePickerDialog.show()
    }
}

interface OnTimePickListener {
    fun onTimePick(hour: Int, minute: Int)
}

interface OnPopupItemClickListener {
    fun onPopupItemClick(title: String, id: Int)
}