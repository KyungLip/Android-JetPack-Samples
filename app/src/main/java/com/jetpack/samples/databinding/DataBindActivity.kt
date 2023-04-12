package com.jetpack.samples.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jetpack.samples.R
import com.jetpack.samples.lifecyle.MainActivityLifecycleObserver

class DataBindActivity : AppCompatActivity() {
    private val user = User("Hello World", 18)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityDataBindBinding>(
            this,
            R.layout.activity_data_bind
        )
        dataBinding.user = user
        dataBinding.btnChange.setOnClickListener {
            user.name="NewName"
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycle.addObserver( MainActivityLifecycleObserver())
    }
}