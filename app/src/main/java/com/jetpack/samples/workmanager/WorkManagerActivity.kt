package com.jetpack.samples.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.jetpack.samples.R
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).build()
        val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            //设置约束条件
            .setConstraints(constraints)
            //设置延迟初始化
            .setInitialDelay(5, TimeUnit.SECONDS)
            //设置标签
            .addTag("UploadWorker")
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }
}