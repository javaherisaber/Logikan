/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util.helper

import android.content.Context
import androidx.annotation.XmlRes
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.StandardExceptionParser
import com.google.android.gms.analytics.Tracker

/**
 * Singleton gateway to work with GoogleAnalytics
 */
object AnalyticsHelper {

    private lateinit var tracker: Tracker

    /**
     * initialize class before using it
     *
     * @param trackerId configuration file located at xml folder eg. R.xml.app_tracker
     */
    @JvmStatic
    fun initialize(context: Context, @XmlRes trackerId: Int) {
        tracker = GoogleAnalytics.getInstance(context).newTracker(trackerId)
    }

    /**
     * Google Analytics
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     *
     * @throws IllegalStateException when class isn't initialized at Application class
     */
    @JvmStatic
    @Throws(IllegalStateException::class)
    fun trackScreenView(context: Context, screenName: String) {
        assertTrackerInitialization()
        tracker.setScreenName(screenName) // Set screen name
        tracker.send(HitBuilders.ScreenViewBuilder().build()) // Send a screen view
        GoogleAnalytics.getInstance(context).dispatchLocalHits()
    }

    /**
     * Google Analytics
     * Tracking exception
     *
     * @param exception exception to be tracked
     *
     * @throws IllegalStateException when class isn't initialized at Application class
     */
    @JvmStatic
    @Throws(IllegalStateException::class)
    fun trackException(context: Context, exception: Exception) {
        assertTrackerInitialization()
        tracker.send(
            HitBuilders.ExceptionBuilder()
                .setDescription(
                    StandardExceptionParser(context, null)
                        .getDescription(Thread.currentThread().name, exception)
                )
                .setFatal(false)
                .build()
        )
    }

    /**
     * Google Analytics
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     *
     * @throws IllegalStateException when class isn't initialized at Application class
     */
    @JvmStatic
    @Throws(IllegalStateException::class)
    fun trackEvent(category: String, action: String, label: String) {
        assertTrackerInitialization()
        // Build and send an Event.
        tracker.send(HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build())
    }

    /**
     * @throws IllegalStateException when tracker isn't initialized
     */
    @Throws(IllegalStateException::class)
    private fun assertTrackerInitialization() {
        if (!tracker.isInitialized) {
            throw IllegalStateException("Class not initialized in BaseApplication")
        }
    }
}