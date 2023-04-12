package com.jetpack.samples

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class MyApplication : Application(), ViewModelStoreOwner {
    private val mViewModelStore = ViewModelStore()

    companion object {
        private lateinit var mContext: Application
        fun getAppContext(): Application {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }
}