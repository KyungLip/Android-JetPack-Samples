package com.jetpack.samples.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jetpack.samples.R
import kotlinx.coroutines.*

class RoomActivity : AppCompatActivity() {
    private lateinit var coroutineScope:CoroutineScope
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
         coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
//            RoomDBHelper.userDao.insertAll(User(uid = 1, firstName = "Hello World1", city = "Beijing1"),
//                User(uid = 2, firstName = "Hello World2", city = "Beijing2"))
            val all = RoomDBHelper.userDao.getAll()
            Log.d("kylp","数据库中的所有数据 $all")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
