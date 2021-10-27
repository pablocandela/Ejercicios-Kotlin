package com.example.earthquakes.api

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.earthquakes.database.getDatabase
import com.example.earthquakes.main.MainRepository

class SyncWorkManager(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "SyncWorkManager"
    }

    private val database = getDatabase(appContext)
    private val repository = MainRepository(database)
    override suspend fun doWork(): Result {
        repository.fetchEarthQuakes(true)
        return Result.success()
    }
}