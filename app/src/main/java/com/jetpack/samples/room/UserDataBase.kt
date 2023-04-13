package com.jetpack.samples.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao():UserDao
}
