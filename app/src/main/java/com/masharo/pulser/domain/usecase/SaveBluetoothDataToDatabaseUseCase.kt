package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.WorkerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SaveBluetoothDataToDatabaseUseCase(
    private val workerRepository: WorkerRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun execute() {
        withContext(dispatcher) {
            workerRepository.saveBluetoothDataToDatabase()
        }
    }

}