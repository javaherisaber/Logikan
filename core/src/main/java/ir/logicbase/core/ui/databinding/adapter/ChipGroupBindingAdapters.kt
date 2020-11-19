package ir.logicbase.core.ui.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ir.logicbase.core.R
import ir.logicbase.core.ui.util.extension.inflateViewWithSynchronizedFont

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
    data: Collection<Pair<Int, String>>?, listener: OnChipCloseIconClickListener?, keepChildren: Boolean?
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
    data: Collection<Triple<Int, String, Boolean>>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?
) = data?.let {
    isSingleSelection = true
    removeAllViews()
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = it.inflate(R.layout.core_view_chip_choice, this, false) as Chip
            chip.id = item.first
            if (chip.id == selectedId) {
                chip.isChecked = true
            }
            chip.text = item.second
            chip.isChipIconVisible = item.third // true if selected any values related to this chip
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
    data: Collection<Pair<Int, String>>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?,
    isChipIconVisible: Boolean?
) = data?.let {
    isSingleSelection = true
    removeAllViews()
    inflateViewWithSynchronizedFont {
        data.forEach { item ->
            val chip = it.inflate(R.layout.core_view_chip_choice, this, false) as Chip
            chip.id = item.first
            chip.text = item.second
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

fun ChipGroup.inflateEntryChip(item: Pair<Int, String>, listener: OnChipCloseIconClickListener?): Chip =
    inflateViewWithSynchronizedFont {
        val chip = it.inflate(R.layout.core_view_chip_entry, this, false) as Chip
        chip.id = item.first
        chip.text = item.second
        chip.setOnCloseIconClickListener { view ->
            this.removeView(view)
            listener?.onCloseIconClick(item.first, item.second)
        }
        return@inflateViewWithSynchronizedFont chip
    }

interface OnChipCloseIconClickListener {
    fun onCloseIconClick(id: Int, title: String)
}

interface OnChipGroupChoiceChangeListener {
    fun onChoiceChanged(isChecked: Boolean, checkedId: Int)
}