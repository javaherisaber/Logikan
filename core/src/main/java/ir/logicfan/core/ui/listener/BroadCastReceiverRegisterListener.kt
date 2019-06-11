package ir.logicfan.core.ui.listener

import android.content.BroadcastReceiver
import android.content.IntentFilter

interface BroadCastReceiverRegisterListener {

    fun registerReceiver(receiver: BroadcastReceiver, filter: IntentFilter)

    fun unregisterReceiver(receiver: BroadcastReceiver)
}
