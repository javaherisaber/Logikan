package ir.logicfan.core.ui.databinding.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapters {

    /**
     * Add a default gray color divider to your recyclerView
     *
     * @param defaultDividerItemDecoration if true itemDecoration will be added
     * @param dividerOrientation if provided it decide orientation, if null Vertical will be used
     */
    @BindingAdapter(
        value = ["defaultDividerItemDecoration", "dividerOrientation"],
        requireAll = false
    )
    @JvmStatic
    fun addDefaultDividerItemDecoration(
        recyclerView: RecyclerView,
        defaultDividerItemDecoration: Boolean?,
        dividerOrientation: Int?
    ) {
        fun addItemDecoration(recyclerView: RecyclerView, orientation: Int) {
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, orientation))
        }
        if (defaultDividerItemDecoration != null && defaultDividerItemDecoration == true) {
            if (dividerOrientation == null) {
                // no orientation provided, use VERTICAL by default
                addItemDecoration(recyclerView, DividerItemDecoration.VERTICAL)
            } else {
                // orientation provided
                addItemDecoration(recyclerView, dividerOrientation)
            }
        }
    }
}