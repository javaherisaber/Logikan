package ir.logicfan.core.ui.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.BR

/**
 * a very simple lifecycle aware Adapter to be used inside RecyclerView
 * this class only display list of data inside single layout
 *
 * @param T data holder type
 * @property itemLayout row view item layout
 * @property bindingVariableId data holder id inside your view layout (BR is a singleton class holds all id's)
 * @property dataSource a list of data to be used inside adapter
 */
open class BasicAdapter<T>(
    @LayoutRes protected val itemLayout: Int,
    private val bindingVariableId: Int = BR.item,
    val dataSource: MutableLiveData<List<T>> = MutableLiveData()
) : RecyclerView.Adapter<BasicViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        val viewHolder = BasicViewHolder<T>(binding, bindingVariableId)
        binding.setLifecycleOwner(viewHolder)  // register for lifecycle events
        return viewHolder
    }

    override fun onBindViewHolder(holder: BasicViewHolder<T>, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = itemLayout

    private fun getItem(position: Int): T? = dataSource.value?.get(position)

    override fun getItemCount(): Int = dataSource.value?.size ?: 0

    override fun onViewAttachedToWindow(holder: BasicViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear() // notify ViewHolder about lifecycle change
    }

    override fun onViewDetachedFromWindow(holder: BasicViewHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear() // notify ViewHolder about lifecycle change
    }
}