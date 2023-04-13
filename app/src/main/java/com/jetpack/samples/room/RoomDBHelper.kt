package com.jetpack.samples.room

import androidx.room.Room
import com.jetpack.samples.MyApplication

object RoomDBHelper {
    private const val DB_NAME = "user"
    val userDao: UserDao

    init {
        val db =
            Room.databaseBuilder(MyApplication.getAppContext(), UserDataBase::class.java, DB_NAME)
                .build()
        userDao = db.userDao()
    }

}