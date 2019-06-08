package ir.logicfan.core.ui.viewholder

import androidx.databinding.ViewDataBinding
import ir.logicfan.core.BR

/**
 * A clickable ViewHolder which can handle a simple click on root ViewGroup
 *
 * @property onViewGroupClickListener callback to handle click on root ViewGroup
 * @property bindingOnViewGroupClickListenerVariableId variable id inside your view layout
 */
class ClickableViewHolder<T>(
    private val onViewGroupClickListener: ViewHolderListener.OnViewGroupClickListener<T>,
    binding: ViewDataBinding,
    private val bindingOnViewGroupClickListenerVariableId: Int = BR.onViewGroupClickListener,
    bindingDataVariableId: Int = BR.item
) : DataViewHolder<T>(binding, bindingDataVariableId) {

    override fun bind(item: T?) {
        binding.setVariable(bindingOnViewGroupClickListenerVariableId, onViewGroupClickListener)
        super.bind(item)
    }
}