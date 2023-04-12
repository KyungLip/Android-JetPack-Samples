package com.jetpack.samples.utils

import android.util.Log

fun logI(msg: String?) {
    Log.i("kylp", msg ?: "null")
}