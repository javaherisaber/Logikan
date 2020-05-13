package ir.logicfan.core.ui.feature.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.ui.base.BaseViewModel
import javax.inject.Inject

class DummyViewModel @Inject constructor(compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    init {
        this._title.value = "Dummy data to showcase your idea"
    }
}