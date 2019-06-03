package ir.logicfan.core.ui.adapter

fun <T> BasicAdapter<T>.replaceDataSource(newDataSource: List<T>?) {
    this.dataSource.value = newDataSource
    notifyDataSetChanged()
}