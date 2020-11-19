package ir.logicbase.core.ui.recyclerview.paging

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

/**
 * Define a base class to paginate list
 */
class RecyclerViewPaginator(val paginator: Paginator) : RecyclerView.OnScrollListener(), Paginator by paginator {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val layoutManager = recyclerView.layoutManager ?: error("LayoutManager should be set")
        if (newState == SCROLL_STATE_IDLE || (layoutManager is LinearLayoutManager && newState == SCROLL_STATE_DRAGGING)) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val firstVisibleItemPosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> throw IllegalStateException("Layout manager ${layoutManager.javaClass.name} is not supported in paginator")
            }

            if (isLastPage || loadingPage()) return

            if (visibleItemCount + firstVisibleItemPosition + prefetchSize() >= totalItemCount) {
                loadNextPage()
            }
        }
    }
}