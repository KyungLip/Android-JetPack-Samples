package com.jetpack.samples.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.samples.databinding.ActivityBindingDemoBinding

class BindingDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBindingDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_binding_demo)
        binding = ActivityBindingDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}