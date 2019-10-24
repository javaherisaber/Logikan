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
        addView(chip)
    }
}