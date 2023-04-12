package com.jetpack.samples.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryController
import com.jetpack.samples.MyApplication
import com.jetpack.samples.R

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.name.observe(this, Observer<String> {
            Log.d("kylp", "name:$it")
        })
        myViewModel.age.observe(this) {
            Log.d("kylp", "age:$it")
        }
        val mediatorLiveData = MediatorLiveData<String>()
        mediatorLiveData.addSource(myViewModel.name) {
            Log.d("kylp", "mediatorLiveData name:$it")
        }
        mediatorLiveData.addSource(myViewModel.age) {
            Log.d("kylp", "mediatorLiveData age:$it")
        }
        mediatorLiveData.observe(this){
            Log.d("kylp", "mediatorLiveData:$it")
        }

        var index=0
        findViewById<Button>(R.id.btn_change_name).setOnClickListener {
            index++
            myViewModel.name.value="name$index"
        }
        findViewById<Button>(R.id.btn_change_age).setOnClickListener {
            index++
//            myViewModel.age.value="age$index"
            mediatorLiveData.value="Mediator:$index"
        }
//        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//        ViewModelProvider.NewInstanceFactory()
//        ViewModelProvider.AndroidViewModelFactory(MyApplication.getAppContext())
//        SavedStateRegistryController.create(this).savedStateRegistry
    }
}