package ir.logicfan.core.ui.recyclerview.paging

import ir.logicfan.core.data.paging.PAGE_SIZE

/**
 * Define an interface to paginate recycler view list in view model classes
 */
interface Paginator {
    /**
     * page to be loaded at the moment
     */
    var currentPage: Int

    /**
     * check if we loaded last page before
     */
    var isLastPage: Boolean

    /**
     * fetch data before reaching end of list
     */
    fun prefetchSize(): Int = PAGE_SIZE / 2

    /**
     * load next page with [currentPage]
     */
    fun loadNextPage()
}