package com.masharo.pulser.domain

interface WorkerRepository {

    suspend fun saveBluetoothDataToDatabase()

}