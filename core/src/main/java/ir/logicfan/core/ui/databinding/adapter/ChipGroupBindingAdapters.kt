package ir.logicfan.core.ui.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
    it.forEach { item ->
        val chip = inflater.inflate(R.layout.core_view_chip_decoration, this, false) as Chip
        chip.text = item
        this.addView(chip)
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
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    it.forEach { item ->
        val chip = this.inflateEntryChip(inflater, item, listener)
        this.addView(chip)
    }
}

fun ChipGroup.inflateEntryChip(
    inflater: LayoutInflater,
    item: Pair<Int, String>,
    listener: OnChipCloseIconClickListener?
): Chip {
    val chip = inflater.inflate(R.layout.core_view_chip_entry, this, false) as Chip
    chip.id = item.first
    chip.text = item.second
    chip.setOnCloseIconClickListener { view ->
        this.removeView(view)
        listener?.onCloseIconClick(item.first, item.second)
    }
    return chip
}

interface OnChipCloseIconClickListener {
    fun onCloseIconClick(id: Int, title: String)
}