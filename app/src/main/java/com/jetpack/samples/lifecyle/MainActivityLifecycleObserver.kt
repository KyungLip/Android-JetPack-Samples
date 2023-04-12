package com.jetpack.samples.lifecyle

import androidx.lifecycle.*
import com.jetpack.samples.utils.logI

class MainActivityLifecycleObserver : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        logI("LifecycleObserver-onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        logI("LifecycleObserver-onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        logI("LifecycleObserver-onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        logI("LifecycleObserver-onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        logI("LifecycleObserver-onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        logI("LifecycleObserver-onDestroy")
    }
}