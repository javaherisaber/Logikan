package ir.logicfan.core.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife

abstract class BaseViewHolder<in T>(v: View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(item: T, position: Int)
    fun setButterKnifeUnbinder(target: Any, source: View) = ButterKnife.bind(target, source)
}