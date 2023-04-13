package com.jetpack.samples.savestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jetpack.samples.R

class SaveStateHandlerActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(SaveStateHandlerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_state_handler)
        viewModel.tag
    }
}