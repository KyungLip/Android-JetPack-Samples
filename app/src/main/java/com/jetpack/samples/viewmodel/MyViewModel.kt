package com.jetpack.samples.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.EmptyCoroutineContext

class MyViewModel : ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData<String>("Init")
    val age: MutableLiveData<String> = MutableLiveData("18")

    override fun onCleared() {
        super.onCleared()
    }
}

class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {

}

class MyViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
//        ViewModelProvider.KeyedFactory
//        ViewModelProvider.OnRequeryFactory
//        ViewModelProvider.NewInstanceFactory
//        ViewModelProvider.AndroidViewModelFactory
    }
}
