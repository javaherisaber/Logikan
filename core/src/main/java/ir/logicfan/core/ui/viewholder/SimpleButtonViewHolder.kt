package ir.logicfan.core.ui.viewholder

import androidx.databinding.ViewDataBinding
import ir.logicfan.core.BR

/**
 * A clickable ViewHolder which can handle a simple click on a button
 *
 * @property onSimpleButtonClickListener callback to handle click on a simple button
 * @property bindingOnSimpleButtonClickListenerVariableId variable id inside your view layout
 */
class SimpleButtonViewHolder<T>(
    private val onSimpleButtonClickListener: ViewHolderListener.OnSimpleButtonClickListener,
    binding: ViewDataBinding,
    private val bindingOnSimpleButtonClickListenerVariableId: Int = BR.onSimpleButtonClickListener,
    bindingDataVariableId: Int = BR.item
) : DataViewHolder<T>(binding, bindingDataVariableId) {

    override fun bind(item: T?) {
        binding.setVariable(bindingOnSimpleButtonClickListenerVariableId, onSimpleButtonClickListener)
        super.bind(item)
    }
}