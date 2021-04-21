package ir.logicbase.logikan.ui.recyclerview.paging

import ir.logicbase.logikan.data.paging.PAGE_SIZE

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
     * is loading current page
     */
    fun loadingPage(): Boolean

    /**
     * fetch data before reaching end of list
     *
     * This variable is used to set
     * the threshold. For instance, if I have
     * set the page limit to 20, this will notify
     * the app to fetch more transactions when the
     * user scrolls to the 18th item of the list.
     */
    fun prefetchSize(): Int = PAGE_SIZE / 2

    /**
     * load next page with [currentPage]
     */
    fun loadNextPage()
}