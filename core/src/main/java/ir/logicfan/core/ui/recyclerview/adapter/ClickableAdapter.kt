package ir.logicfan.core.ui.recyclerview.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import ir.logicfan.core.BR
import ir.logicfan.core.ui.recyclerview.viewholder.ClickableViewHolder
import ir.logicfan.core.ui.recyclerview.viewholder.DataViewHolder
import ir.logicfan.core.ui.recyclerview.viewholder.ViewHolderListener

/**
 * Define an adapter which can handle click on root ViewGroup
 *
 * @property onViewGroupClickListener callback to handle click on root ViewGroup
 * @property bindingOnViewGroupClickListenerVariableId variable id inside your view layout
 */
class ClickableAdapter<T>(
    private val onViewGroupClickListener: ViewHolderListener.OnViewGroupClickListener<T>,
    @LayoutRes itemLayout: Int,
    private val bindingOnViewGroupClickListenerVariableId: Int = BR.onViewGroupClickListener,
    bindingVariableId: Int = BR.item,
    diffCallback: DiffUtil.ItemCallback<T>
) : SingleDataAdapter<T>(itemLayout, bindingVariableId, diffCallback) {

    override fun provideViewHolder(binding: ViewDataBinding): DataViewHolder<T> =
        ClickableViewHolder(
            onViewGroupClickListener,
            binding,
            bindingOnViewGroupClickListenerVariableId,
            bindingDataVariableId
        )
}