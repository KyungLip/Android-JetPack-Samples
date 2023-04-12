package com.jetpack.samples.lifecyle

import androidx.annotation.RestrictTo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
@RestrictTo(RestrictTo.Scope.LIBRARY)
class MyLifecycleOwner : LifecycleOwner {
    val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}