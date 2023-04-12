package com.jetpack.samples.lifecyle

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class MiniReportFragment : Fragment() {
    companion object {
        private const val REPORT_FRAGMENT_TAG:String = "com.example.kotlindemo.lifecyle.MiniReportFragment"
        fun injectIfNeededIn(activity:Activity) {
            if (Build.VERSION.SDK_INT >= 29) {
                MiniLifecycleCallbacks.registerIn(activity)
            }
            val fragmentManager = activity.fragmentManager
            if (fragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
                fragmentManager?.beginTransaction()?.add(MiniReportFragment(), REPORT_FRAGMENT_TAG)
                fragmentManager.executePendingTransactions()
            }
        }

        fun dispatch(activity: Activity?, event: Lifecycle.Event) {
            ((activity as? LifecycleOwner)?.lifecycle as? LifecycleRegistry)?.handleLifecycleEvent(
                event
            )
        }

        fun get(activity: FragmentActivity): MiniReportFragment? {
            return activity.supportFragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) as? MiniReportFragment
        }
    }

    interface ActivityInitializationListener {
        fun onCreate()
        fun onStart()
        fun onResume()
    }

    private var mProcessListener: ActivityInitializationListener? = null

    fun setProcessListener(activityInitializationListener: ActivityInitializationListener) {
        this.mProcessListener = activityInitializationListener
    }

    private fun dispatchCreate(listener: ActivityInitializationListener?) {
        listener?.onCreate()
    }

    private fun dispatchStart(listener: ActivityInitializationListener?) {
        listener?.onStart()
    }

    private fun dispatchResume(listener: ActivityInitializationListener?) {
        listener?.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dispatchCreate(mProcessListener)
        dispatch(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        dispatchCreate(mProcessListener)
        dispatch(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        dispatchCreate(mProcessListener)
        dispatch(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        dispatch(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        dispatch(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        dispatch(Lifecycle.Event.ON_DESTROY)
        // just want to be sure that we won't leak reference to an activity
        mProcessListener = null;
    }

    private fun dispatch(event: Lifecycle.Event) {
        if (Build.VERSION.SDK_INT < 29) {
            Companion.dispatch(activity, event)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    class MiniLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        companion object {
            fun registerIn(activity: Activity) {
                activity.registerActivityLifecycleCallbacks(MiniLifecycleCallbacks())
            }
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityPostCreated(activity: Activity, savedInstanceState: Bundle?) {
            super.onActivityPostCreated(activity, savedInstanceState)
            dispatch(activity, Lifecycle.Event.ON_CREATE)
        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityPostStarted(activity: Activity) {
            super.onActivityPostStarted(activity)
            dispatch(activity, Lifecycle.Event.ON_START)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPostResumed(activity: Activity) {
            super.onActivityPostResumed(activity)
            dispatch(activity, Lifecycle.Event.ON_RESUME)
        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityPostPaused(activity: Activity) {
            super.onActivityPostPaused(activity)
            dispatch(activity, Lifecycle.Event.ON_PAUSE)
        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivityPostStopped(activity: Activity) {
            super.onActivityPostStopped(activity)
            dispatch(activity, Lifecycle.Event.ON_STOP)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }

        override fun onActivityPostDestroyed(activity: Activity) {
            super.onActivityPostDestroyed(activity)
            dispatch(activity, Lifecycle.Event.ON_DESTROY)
        }
    }

}