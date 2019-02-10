package ir.logicfan.core.ui.common;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public interface BroadCastReceiverRegisterListener {

    void registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiver(BroadcastReceiver receiver);
}
