package ir.logicfan.core.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class NetworkConnectivityViewModel @Inject constructor() : ViewModel() {

    private val _alertPanelVisibility = MutableLiveData<Boolean>()
    val alertPanelVisibility: LiveData<Boolean> = _alertPanelVisibility

    // gets called only when there was an offline state, and network becomes available
    private val _networkBecomesAvailable = LiveEvent<Unit>()
    val networkBecomesAvailable: LiveData<Unit> = _networkBecomesAvailable

    fun onNetworkAvailabilityChange(isActive: Boolean) {
        if (isActive) {
            _alertPanelVisibility.value = false
            _networkBecomesAvailable.value = Unit
        } else {
            if (_alertPanelVisibility.value == null || _alertPanelVisibility.value == false) {
                _alertPanelVisibility.value = true
            }
        }
    }

    fun onCloseClick() {
        _alertPanelVisibility.value = false
    }
}