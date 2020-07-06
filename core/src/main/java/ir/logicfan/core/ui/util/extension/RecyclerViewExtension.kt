package ir.logicfan.core.ui.util.extension

import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.ui.recyclerview.paging.Paginator
import ir.logicfan.core.ui.recyclerview.paging.RecyclerViewPaginator

/**
 * Add paginator to load page by page
 */
fun RecyclerView.addPaginator(paginator: Paginator) = this.addOnScrollListener(object : RecyclerViewPaginator() {
    override val isLastPage: Boolean
        get() = paginator.isLastPage

    override fun loadMore() = paginator.loadNextPage()
})