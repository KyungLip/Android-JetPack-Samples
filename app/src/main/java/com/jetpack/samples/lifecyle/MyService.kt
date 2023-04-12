package com.jetpack.samples.lifecyle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleService

/**
 * 感知Service生命周期需要继承 LifecycleService。
 */
class MyService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        lifecycle.addObserver(object :DefaultLifecycleObserver{

        })
    }
}