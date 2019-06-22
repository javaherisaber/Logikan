package ir.logicfan.core.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ir.logicfan.core.BR
import ir.logicfan.core.data.base.AppExecutors
import ir.logicfan.core.ui.recyclerview.viewholder.DataBindingViewHolder

/**
 * Define an adapter which can display an adapter with single data source
 *
 * @property itemLayout row view item layout
 * @property bindingItemVariableId data variable id inside your view layout
 * (BR is a singleton class generated by data binding to holds all kind of variable id's)
 * @property viewOnClickListenerToBindingIdMap simple view click listener
 * @property dataClickListenerToBindingIdMap click listener which passes data item parameter when clicked
 *
 * @param diffCallback determine a way to compare two items (ListAdapter needs this when adding new items)
 * @param T type of data holder
 */
open class SingleDataAdapter<T>(
    appExecutors: AppExecutors,
    @LayoutRes protected val itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val bindingItemVariableId: Int? = BR.item
) : ListAdapter<T, DataBindingViewHolder<T>>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .setBackgroundThreadExecutor(appExecutors.diskIO())
        .build()
) {

    private val viewOnClickListenerToBindingIdMap = HashMap<View.OnClickListener, Int>()
    private val dataClickListenerToBindingIdMap = HashMap<DataBindingViewHolder.OnDataClickListener<T>, Int>()

    fun addDataClickListener(
        onDataClickListener: DataBindingViewHolder.OnDataClickListener<T>,
        bindingVariableId: Int
    ) {
        dataClickListenerToBindingIdMap[onDataClickListener] = bindingVariableId
    }

    fun addViewListener(onClickListener: View.OnClickListener, bindingVariableId: Int) {
        viewOnClickListenerToBindingIdMap[onClickListener] = bindingVariableId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        return createViewHolder(parent)
    }

    open fun createViewHolder(parent: ViewGroup): DataBindingViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), itemLayout, parent, false
        )
        val viewHolder = provideViewHolder(binding)
        binding.setLifecycleOwner(viewHolder)  // register for lifecycle events
        viewHolder.addViewOnClickListener(viewOnClickListenerToBindingIdMap)
        viewHolder.addDataClickListener(dataClickListenerToBindingIdMap)
        return viewHolder
    }

    open fun provideViewHolder(binding: ViewDataBinding): DataBindingViewHolder<T> = DataBindingViewHolder(binding)

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
        bindingItemVariableId?.let {
            holder.addItemBinding(getItem(position), bindingItemVariableId)
        }
        holder.bind()
    }

    override fun getItemViewType(position: Int): Int = itemLayout

    override fun onViewAttachedToWindow(holder: DataBindingViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear() // notify ViewHolder about lifecycle change
    }

    override fun onViewDetachedFromWindow(holder: DataBindingViewHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear() // notify ViewHolder about lifecycle change
    }
}