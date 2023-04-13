package com.jetpack.samples.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.jetpack.samples.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)
        lifecycleScope.launch {
            dataStore.edit {
                it[nameKey] = "Hello World"
                it[ageKey] = 18
            }
           val nameFlow= dataStore.data.map {
                it[nameKey]
            }
            val name=nameFlow.first()
            Log.d("kylp","获取到的数据为:$name")
//            nameFlow.collect{name->
//                Log.d("kylp","获取到的数据为:$name")
//            }

        }

    }
}