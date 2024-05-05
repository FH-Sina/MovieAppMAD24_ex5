package com.example.movieappmad24.com.example.movieappmad24.functions


import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class DatabaseRepositoryManager(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun seedDatabase() {
        val seedWorkRequest = OneTimeWorkRequestBuilder<MovieDataSeederWorker>()
            .build()

        workManager.enqueue(seedWorkRequest)
    }
}