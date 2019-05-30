package ir.logicfan.core.ui.basic

fun <T> BasicAdapter<T>.replaceDataSource(newDataSource: List<T>?) {
    this.dataSource.value = newDataSource
    notifyDataSetChanged()
}