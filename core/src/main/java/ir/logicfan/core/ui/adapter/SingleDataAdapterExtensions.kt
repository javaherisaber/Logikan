package ir.logicfan.core.ui.adapter

fun <T> SingleDataAdapter<T>.replaceDataSource(newDataSource: List<T>?) {
    this.dataSource.value = newDataSource
    notifyDataSetChanged()
}