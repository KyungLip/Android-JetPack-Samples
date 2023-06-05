package com.jetpack.samples.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.samples.R
import com.jetpack.samples.databinding.ActivityBindingDemoBinding

class BindingDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBindingDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_binding_demo)
        //1：创建绑定类的实例
          //1.1
        binding = ActivityBindingDemoBinding.inflate(layoutInflater)
//        //1.2
//        binding = ActivityBindingDemoBinding.inflate(layoutInflater, null, false)
//        //1.3
//        val rootVIew = layoutInflater.inflate(R.layout.activity_binding_demo, null, false)
//        binding = ActivityBindingDemoBinding.bind(rootVIew)

        //2：获取根视图的引用
        val rootView = binding.root
        //3:调用setContentView()
        setContentView(rootView)

        //使用控件
        binding.tvTitle.setOnClickListener {

        }
    }
}