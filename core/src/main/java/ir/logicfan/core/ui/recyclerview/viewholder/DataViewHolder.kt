package ir.logicfan.core.ui.recyclerview.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding

/**
 * Define a view holder with data item or DataClickListeners
 *
 * @property itemDataToBindingId item binding data and variable id as a pair
 * @property dataClickListenerToBindingIdMap click listener which passes data item parameter when clicked
 *
 * @param T type of data holder
 */
open class DataViewHolder<T>(itemView: View) : SimpleViewHolder(itemView) {

    private lateinit var itemDataToBindingId: Pair<T, Int>
    private var dataClickListenerToBindingIdMap = HashMap<OnDataClickListener<T>, Int>()

    /**
     * Define a click listener with carrying data
     *
     * @param R type of data being passed through this listener
     * (You can use same data in your item or any other types, maybe you only need a single ID)
     */
    interface OnDataClickListener<R> {
        fun onViewHolderClick(view: View, data: R)
    }

    fun addItemBinding(item: T, bindingId: Int) {
        itemDataToBindingId = item to bindingId
    }

    fun addDataClickListener(onDataClickListener: OnDataClickListener<T>, bindingVariableId: Int) {
        dataClickListenerToBindingIdMap[onDataClickListener] = bindingVariableId
    }

    fun addDataClickListener(dataClickListenerToBindingIdMap: HashMap<OnDataClickListener<T>, Int>) {
        this.dataClickListenerToBindingIdMap = dataClickListenerToBindingIdMap
    }

    override fun bind(binding: ViewDataBinding) {
        if (this::itemDataToBindingId.isInitialized) {
            binding.setVariable(itemDataToBindingId.second, itemDataToBindingId.first)
        }
        for ((listener, variableId) in dataClickListenerToBindingIdMap) {
            binding.setVariable(variableId, listener)
        }
        super.bind(binding)
    }
}