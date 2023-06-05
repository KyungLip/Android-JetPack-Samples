package com.jetpack.samples.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jetpack.samples.R
import com.jetpack.samples.lifecyle.MainActivityLifecycleObserver

class DataBindActivity : AppCompatActivity() {
    companion object{
        private const val TAG="kylp"
    }
    private lateinit var mDataBinding: ActivityDataBindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.创建绑定类
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        //2.使用控件
        mDataBinding.btnChange.setOnClickListener {
            Log.d(TAG,"布局文件中对应的id为btn_change的Button被点击了！")
        }
        //3.绑定数据类
        mDataBinding.user=User("Hello World",18)
    }
}