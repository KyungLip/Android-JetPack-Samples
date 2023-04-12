package com.jetpack.samples.lifecyle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.*
import com.jetpack.samples.R

class LifecycleActivity : Activity(), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        MiniReportFragment.injectIfNeededIn(this)
        lifecycleRegistry.addObserver(MainActivityLifecycleObserver())

        //Process生命周期感知
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {

        })
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}