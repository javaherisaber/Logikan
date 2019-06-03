package ir.logicfan.core.ui.viewholder

import android.view.View

/**
 * To be used as a callback inside RecyclerView ViewHolder
 */
interface ViewHolderListener {

    /**
     * When clicked on root ViewGroup
     *
     * @param T data item of a ViewHolder
     */
    interface OnViewGroupClickListener <T> {
        fun onViewHolderClick(data: T, view: View)
    }
}