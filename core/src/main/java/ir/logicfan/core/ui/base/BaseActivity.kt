package ir.logicfan.core.ui.base

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import ir.logicfan.core.data.preferences.BaseSharedPreferences
import ir.logicfan.core.ui.error.DataErrorResolver
import ir.logicfan.core.ui.error.DataResolution
import ir.logicfan.core.ui.util.LocaleUtils
import javax.inject.Inject

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

abstract class BaseActivity : DaggerAppCompatActivity(), DataResolution {

    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    var dataErrorResolver: DataErrorResolver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataErrorResolver = DataErrorResolver(this, this)
        LocaleUtils.setLocale(this, baseSharedPreferences.localeSetting)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onResolutionStart(throwable: Throwable) {
        dataErrorResolver?.onResolutionStart(throwable)
    }

    override fun onDataUnexpectedError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClientError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIOError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoSuchResourceError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUnauthorizedError(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOtherHttpError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectivityAvailable(localizedMessage: String?, throwable: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectivityUnavailable(localizedMessage: String, throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
