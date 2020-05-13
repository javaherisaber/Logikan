package ir.logicfan.core.ui.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.resources.TextAppearanceConfig
import ir.logicfan.core.R

/**
 * Inflate Chip with decoration style into ChipGroup
 */
@BindingAdapter("decorationChipsData")
fun ChipGroup.addDecorationChips(data: Collection<String>?) = data?.let {
    if (this.childCount > 0) {
        this.removeAllViews()
    }
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    it.forEach { item ->
        val chip = inflater.inflate(R.layout.core_view_chip_decoration, this, false) as Chip
        chip.text = item
        this.addView(chip)
    }
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
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
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    it.forEach { item ->
        val chip = this.inflateEntryChip(inflater, item, listener)
        this.addView(chip)
    }
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
}

/**
 * add choice chips to this ChipGroup
 * @param chipsData inflate chip with first as title and second as id also enable or disable icon with third property
 */
@BindingAdapter(value = ["choiceChipsData", "onChoiceChange", "selectedChoiceChip"], requireAll = false)
fun ChipGroup.addChoiceChipsChildren(
    chipsData: Collection<Triple<Int, String, Boolean>>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?
) = chipsData?.let {
    removeAllViews()
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    it.forEach { item ->
        val chip = inflater.inflate(R.layout.core_view_chip_choice, this, false) as Chip
        chip.id = item.first
        if (chip.id == selectedId) {
            chip.isChecked = true
        }
        chip.text = item.second
        chip.isChipIconVisible = item.third // true if selected any values related to this chip
        addView(chip)
    }
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
    this.setOnChoiceChangeListener(onChoiceChangeListener)
}

/**
 * add choice chips to this ChipGroup
 * enable icon when user select a chip and disable when unselected
 */
@BindingAdapter(value = ["choiceChipsData", "onChoiceChange", "selectedChoiceChip", "isChipIconVisible"], requireAll = false)
fun ChipGroup.addSingleDotChoiceChipsChildren(
    chipsData: Collection<Pair<Int, String>>?,
    onChoiceChangeListener: OnChipGroupChoiceChangeListener?,
    selectedId: Int?,
    isChipIconVisible: Boolean?
) = chipsData?.let {
    removeAllViews()
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    it.forEach { item ->
        val chip = inflater.inflate(R.layout.core_view_chip_choice, this, false) as Chip
        chip.id = item.first
        chip.text = item.second
        if (chip.id == selectedId) {
            chip.isChecked = true
        }
        chip.isChipIconVisible = isChipIconVisible ?: false
        chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChipIconVisible != null && isChipIconVisible) {
                chip.isChipIconVisible = isChecked
            }
        }
        addView(chip)
    }
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
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

fun ChipGroup.inflateEntryChip(
    inflater: LayoutInflater,
    item: Pair<Int, String>,
    listener: OnChipCloseIconClickListener?
): Chip {
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    val chip = inflater.inflate(R.layout.core_view_chip_entry, this, false) as Chip
    chip.id = item.first
    chip.text = item.second
    chip.setOnCloseIconClickListener { view ->
        this.removeView(view)
        listener?.onCloseIconClick(item.first, item.second)
    }
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
    return chip
}

interface OnChipCloseIconClickListener {
    fun onCloseIconClick(id: Int, title: String)
}

interface OnChipGroupChoiceChangeListener {
    fun onChoiceChanged(isChecked: Boolean, checkedId: Int)
}