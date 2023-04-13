package com.jetpack.samples.savestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.savedstate.SavedStateRegistry
import com.jetpack.samples.R

class SaveStateActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "kylp"
        private const val PROVIDER_KEY = "restore_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_state)
        if (savedStateRegistry.isRestored) {
            val bundle = savedStateRegistry.consumeRestoredStateForKey(PROVIDER_KEY)
            val restoreData = bundle?.getString(DataProvider.DATA_KEY)
            Log.d(TAG, "恢复过来的数据为:$restoreData")
        }
        savedStateRegistry.registerSavedStateProvider(PROVIDER_KEY, DataProvider())
        if(savedInstanceState!=null){
            val restoreData=savedInstanceState.getString("savekey01")

            Log.d(TAG,"onCreate:restoreData $restoreData")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"onSaveInstanceState")
        outState.putString("savekey01","outState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreData=savedInstanceState.getString("savekey01")
        Log.d(TAG,"onRestoreInstanceState:restoreData $restoreData")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
        savedStateRegistry.unregisterSavedStateProvider(PROVIDER_KEY)
    }
    class DataProvider : SavedStateRegistry.SavedStateProvider {
        companion object {
            val DATA_KEY = "data_key"
        }

        override fun saveState(): Bundle {
            return Bundle().apply {
                putString(DATA_KEY, "存储的数据")
            }
        }
    }
}
