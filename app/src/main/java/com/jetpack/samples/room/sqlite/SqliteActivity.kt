package com.jetpack.samples.room.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jetpack.samples.MyApplication
import com.jetpack.samples.R
import kotlin.system.measureTimeMillis

class SqliteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        PlayerSqliteHelper.getInstance().insert(Player("Hello World", 18))
        val allPlayers: List<Player>
        val time = measureTimeMillis {
            allPlayers = PlayerSqliteHelper.getInstance().queryAll()
        }
        Log.d("kylp", "数据库中的数据:${allPlayers} $time")

    }
}