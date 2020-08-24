package ir.logicfan.core.ui.recyclerview.paging

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
     * load next page with [currentPage]
     */
    fun loadNextPage()
}