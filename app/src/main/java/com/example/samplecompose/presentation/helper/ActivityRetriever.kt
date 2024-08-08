package com.example.samplecompose.presentation.helper

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class ActivityRetriever(context: Context) : Application.ActivityLifecycleCallbacks {
    private var mCreatedActivities = arrayListOf<Activity>()
    private var mStartedActivities = arrayListOf<Activity>()

    init {
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(this)
    }

    fun getOrNull(): Activity? {
        return mStartedActivities.lastOrNull() ?: mCreatedActivities.lastOrNull()
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        mCreatedActivities.add(p0)
    }

    override fun onActivityStarted(p0: Activity) {
        mStartedActivities.add(p0)
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
        mStartedActivities.add(p0)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
        mCreatedActivities.remove(p0)
    }

}