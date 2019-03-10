package ir.logicfan.core.ui.common


import ir.logicfan.core.data.network.error.NetworkErrorType

interface ViewContract {
    fun showProgress()
    fun hideProgress()
    fun showError(error: NetworkErrorType)
}
