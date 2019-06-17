package ir.logicfan.core.ui.recyclerview.viewholder

import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

/**
 * A very simple lifecycle aware ViewHolder to be used inside a RecyclerView.Adapter
 *
 * @property binding data binding handle to bind your layout
 * @property viewOnClickListenerToBindingIdMap simple view click listener
 *
 * @param itemView viewGroup which this layout will reside into it
 */
open class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleOwner {

    private lateinit var binding: ViewDataBinding
    private var viewOnClickListenerToBindingIdMap = HashMap<View.OnClickListener, Int>()

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

    fun addViewOnClickListener(onClickListener: View.OnClickListener, bindingVariableId: Int) {
        viewOnClickListenerToBindingIdMap[onClickListener] = bindingVariableId
    }

    fun addViewOnClickListener(viewOnClickListenerToBindingIdMap: HashMap<View.OnClickListener, Int>) {
        this.viewOnClickListenerToBindingIdMap = viewOnClickListenerToBindingIdMap
    }

    /**
     * bind variables to data binding
     *
     * @param binding data binding handle to bind your layout
     */
    @CallSuper // derived function must call super (at the end of your own implementation)
    open fun bind(binding: ViewDataBinding) {
        this.binding = binding
        for ((listener, variableId) in viewOnClickListenerToBindingIdMap) {
            binding.setVariable(variableId, listener)
        }
        binding.executePendingBindings()
    }

    /**
     * Release binding variables and resources
     */
    private fun unbind() {
        if (this::binding.isInitialized) {
            binding.unbind()
        }
    }
}