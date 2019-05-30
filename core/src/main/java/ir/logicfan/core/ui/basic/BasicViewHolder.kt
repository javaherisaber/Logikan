package ir.logicfan.core.ui.basic

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.BR

/**
 * A very simple lifecycle aware ViewHolder to be used inside a RecyclerView.Adapter
 * this class only display given data in layout
 *
 * @param T data holder type
 * @property binding view data binding
 * @property bindingVariableId data holder id inside your view layout (BR is a singleton class holds all id's)
 */
open class BasicViewHolder<T>(private val binding: ViewDataBinding, private val bindingVariableId: Int = BR.item) :
    RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    /**
     * to be used by LifecycleOwner
     */
    private val lifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    init {
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
    }

    fun onAppear() = lifecycleRegistry.markState(Lifecycle.State.CREATED)

    fun onDisappear() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        unbind()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    /**
     * bind's data holder to view layout variable
     *
     * @param item data holder
     */
    fun bind(item: T?) {
        binding.setVariable(bindingVariableId, item)
        binding.executePendingBindings()
    }

    /**
     * Release binding variables and resources
     */
    fun unbind() {
        binding.unbind()
    }
}