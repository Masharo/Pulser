package com.masharo.pulser.data.repository

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.masharo.pulser.data.worker.BluetoothToDatabaseWorker
import com.masharo.pulser.domain.WorkerRepository

class WorkerRepositoryImpl(
    private val context: Context
): WorkerRepository {

    override suspend fun saveBluetoothDataToDatabase() {
        WorkManager
            .getInstance(context)
            .enqueue(
                OneTimeWorkRequestBuilder<BluetoothToDatabaseWorker>()
                    .build()
            )
    }

}