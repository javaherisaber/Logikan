package ir.logicfan.core.ui.base

fun <T> BasicAdapter<T>.replaceDataSource(newDataSource: List<T>?) {
    this.dataSource.value = newDataSource
    notifyDataSetChanged()
}