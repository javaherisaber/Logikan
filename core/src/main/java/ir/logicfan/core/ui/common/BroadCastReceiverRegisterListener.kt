package ir.logicfan.core.ui.common

import android.content.BroadcastReceiver
import android.content.IntentFilter

interface BroadCastReceiverRegisterListener {

    fun registerReceiver(receiver: BroadcastReceiver, filter: IntentFilter)

    fun unregisterReceiver(receiver: BroadcastReceiver)
}
