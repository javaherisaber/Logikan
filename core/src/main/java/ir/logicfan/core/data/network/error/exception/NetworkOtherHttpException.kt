package ir.logicfan.core.data.network.error.exception

import ir.logicfan.core.data.network.error.NetworkException

/**
 * When there is a custom response code outside of 400 - 599
 */
class NetworkOtherHttpException : NetworkException()