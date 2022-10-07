package com.masharo.pulser.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masharo.pulser.domain.BluetoothRepository
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetDataDeviceUseCase(
    private val bluetoothRepository: BluetoothRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun execute(device: Device): LiveData<PulseData> {
        val resultLive = MutableLiveData<PulseData>()

        withContext(dispatcher) {
            resultLive.postValue(bluetoothRepository.connectDevice(device))
        }

        return resultLive
    }

}