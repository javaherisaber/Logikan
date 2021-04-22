@file:JvmName("ChipGroupBindingAdapters")

package ir.logicbase.logikan.ui.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ir.logicbase.logikan.ui.util.extension.inflateViewWithSynchronizedFont
import ir.logicbase.logikan.ui.R

/**
 * Inflate Chip with decoration style into ChipGroup
 */
@BindingAdapter("decorationChipsData")
fun ChipGroup.addDecorationChips(data: Collection<String>?) = data?.let {
    if (this.childCount > 0) {
        this.removeAllViews()
    }
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = it.inflate(R.layout.core_view_chip_decoration, this, false) as Chip
            chip.text = item
            this.addView(chip)
        }
    }
}

/**
 * Inflate Chip with entry style into ChipGroup
 */
@BindingAdapter(value = ["entryChipsData", "onCloseIconClick", "keepChildren"], requireAll = false)
fun ChipGroup.addEntryChips(
    data: Collection<ChipGroupData>?, listener: OnChipCloseIconClickListener?, keepChildren: Boolean?
) = data?.let {
    if (this.childCount > 0 && (keepChildren == false || keepChildren == null)) {
        removeAllViews()
    }
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = this.inflateEntryChip(item, listener)
            this.addView(chip)
        }
    }
}

/**
 * add choice chips to this ChipGroup
 * @param data inflate chip with first as title and second as id also enable or disable icon with third property
 */
@BindingAdapter(value = ["choiceChipsData", "onChoiceChange", "selectedChoiceChip"], requireAll = false)
fun ChipGroup.addChoiceChipsChildren(
    data: Collection<ChoiceChipGroupData>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?
) = data?.let {
    isSingleSelection = true
    removeAllViews()
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = it.inflate(R.layout.core_view_chip_choice, this, false) as Chip
            chip.id = item.id
            if (chip.id == selectedId) {
                chip.isChecked = true
            }
            chip.text = item.title
            chip.isChipIconVisible = item.displayChipIcon // true if selected any values related to this chip
            addView(chip)
        }
    }
    this.setOnChoiceChangeListener(onChoiceChangeListener)
}

/**
 * add choice chips to this ChipGroup
 * enable icon when user select a chip and disable when unselected
 */
@BindingAdapter(
    value = ["choiceChipsData", "onChoiceChange", "selectedChoiceChip", "isChipIconVisible"],
    requireAll = false
)
fun ChipGroup.addSingleDotChoiceChipsChildren(
    data: Collection<ChipGroupData>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?,
    isChipIconVisible: Boolean?
) = data?.let {
    isSingleSelection = true
    removeAllViews()
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = it.inflate(R.layout.core_view_chip_choice, this, false) as Chip
            chip.id = item.id
            chip.text = item.title
            if (chip.id == selectedId) {
                chip.isChecked = true
                chip.isChipIconVisible = isChipIconVisible ?: false
            } else {
                chip.isChipIconVisible = false
            }
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChipIconVisible != null && isChipIconVisible) {
                    chip.isChipIconVisible = isChecked
                }
            }
            addView(chip)
        }
    }
    this.setOnChoiceChangeListener(onChoiceChangeListener)
}

fun ChipGroup.setOnChoiceChangeListener(
    listener: OnChipGroupChoiceChangeListener?
) = this.setOnCheckedChangeListener { _, checkedId ->
    if (checkedId == View.NO_ID) {
        listener?.onChoiceChanged(false, View.NO_ID)
    } else {
        listener?.onChoiceChanged(true, checkedId)
    }
}

fun ChipGroup.inflateEntryChip(item: ChipGroupData, listener: OnChipCloseIconClickListener?): Chip =
    inflateViewWithSynchronizedFont {
        val chip = it.inflate(R.layout.core_view_chip_entry, this, false) as Chip
        chip.id = item.id
        chip.text = item.title
        chip.setOnCloseIconClickListener { view ->
            this.removeView(view)
            listener?.onCloseIconClick(item)
        }
        return@inflateViewWithSynchronizedFont chip
    }

fun interface OnChipCloseIconClickListener {
    fun onCloseIconClick(item: ChipGroupData)
}

fun interface OnChipGroupChoiceChangeListener {
    fun onChoiceChanged(isChecked: Boolean, checkedId: Int)
}

data class ChipGroupData(val id: Int, val title: String)

data class ChoiceChipGroupData(val id: Int, val title: String, val displayChipIcon: Boolean)