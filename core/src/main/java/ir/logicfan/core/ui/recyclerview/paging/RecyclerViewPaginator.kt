package ir.logicfan.core.ui.recyclerview.paging;

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import ir.logicfan.core.data.paging.PAGE_SIZE

/**
 * Define a base class to paginate list
 */
abstract class RecyclerViewPaginator : RecyclerView.OnScrollListener() {

    /*
     * This variable is used to set
     * the threshold. For instance, if I have
     * set the page limit to 20, this will notify
     * the app to fetch more transactions when the
     * user scrolls to the 18th item of the list.
     * */
    private val prefetchSize = PAGE_SIZE

    abstract val isLastPage: Boolean

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            val layoutManager = recyclerView.layoutManager ?: error("LayoutManager should be set")
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val firstVisibleItemPosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> throw IllegalStateException("Layout manager ${layoutManager.javaClass.name} is not supported in paginator")
            }

            if (isLastPage) return

            if (visibleItemCount + firstVisibleItemPosition + prefetchSize >= totalItemCount) {
                loadMore()
            }
        }
    }

    abstract fun loadMore()
}