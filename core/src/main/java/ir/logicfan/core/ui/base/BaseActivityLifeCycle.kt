package ir.logicfan.core.ui.base

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

/**
 * Listen to callback of every activity
 */
open class BaseActivityLifeCycle : ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        // Track screen view (Analytics)
        //String screenName = activity.getClass().getSimpleName();
        //AnalyticsHelper.getInstance().trackScreenView(activity.getApplicationContext(), screenName);
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}
