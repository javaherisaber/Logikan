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
open class DataBindingViewHolder<T>(binding: ViewDataBinding) : SimpleBindingViewHolder(binding) {

    private lateinit var itemDataToBindingId: Pair<T, Int>
    private var dataToBindingIdMap = HashMap<Any, Int>()
    private var dataClickListenerToBindingIdMap = HashMap<OnDataClickListener<T>, Int>()
    private var positionalDataClickListenerToBindingIdMap = HashMap<OnPositionalDataClickListener<T>, Int>()

    /**
     * Define a click listener with carrying data
     *
     * @param R type of data being passed through this listener
     * (You can use same data in your item or any other types, maybe you only need a single ID)
     */
    interface OnDataClickListener<R> {
        fun onViewHolderClick(view: View, data: R)
    }

    interface OnPositionalDataClickListener<R> {
        fun onViewHolderClick(view: View, data: R, position: Int)
    }

    fun addItemBinding(item: T, bindingId: Int) {
        itemDataToBindingId = item to bindingId
    }

    fun addDataBinding(dataToBindingIdMap: HashMap<Any, Int>) {
        this.dataToBindingIdMap = dataToBindingIdMap
    }

    fun addPositionalDataClickListener(positionalDataClickListenerToBindingIdMap: HashMap<OnPositionalDataClickListener<T>, Int>) {
        this.positionalDataClickListenerToBindingIdMap = positionalDataClickListenerToBindingIdMap
    }

    fun addDataClickListener(dataClickListenerToBindingIdMap: HashMap<OnDataClickListener<T>, Int>) {
        this.dataClickListenerToBindingIdMap = dataClickListenerToBindingIdMap
    }

    @Throws(RuntimeException::class)
    fun rebindItem(item: T) {
        if (this::itemDataToBindingId.isInitialized) {
            binding.setVariable(itemDataToBindingId.second, item)
            rebind()
        } else {
            throw RuntimeException("You cannot call rebind unless you bind before")
        }
    }

    override fun bind() {
        if (this::itemDataToBindingId.isInitialized) {
            binding.setVariable(itemDataToBindingId.second, itemDataToBindingId.first)
        }
        for ((data, variableId) in dataToBindingIdMap) {
            binding.setVariable(variableId, data)
        }
        for ((listener, variableId) in dataClickListenerToBindingIdMap) {
            binding.setVariable(variableId, listener)
        }
        for ((listener, variableId) in positionalDataClickListenerToBindingIdMap) {
            binding.setVariable(variableId, listener)
        }
        super.bind()
    }
}