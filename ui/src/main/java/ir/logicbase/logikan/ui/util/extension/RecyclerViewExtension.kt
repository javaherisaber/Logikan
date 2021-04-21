package ir.logicbase.logikan.ui.util.extension

import androidx.recyclerview.widget.RecyclerView
import ir.logicbase.logikan.ui.recyclerview.paging.Paginator
import ir.logicbase.logikan.ui.recyclerview.paging.RecyclerViewPaginator

/**
 * Add paginator to load page by page
 */
fun RecyclerView.addPaginator(paginator: Paginator) = this.addOnScrollListener(RecyclerViewPaginator(paginator))