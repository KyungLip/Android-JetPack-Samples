package com.jetpack.samples.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "name")
    val firstName: String?,
    @ColumnInfo(name = "city")
    val city: String?
)
