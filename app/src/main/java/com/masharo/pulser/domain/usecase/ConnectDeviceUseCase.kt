package com.masharo.pulser.domain.usecase

import androidx.lifecycle.LiveData
import com.masharo.pulser.domain.BluetoothRepository
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device

class ConnectDeviceUseCase(
    private val bluetoothRepository: BluetoothRepository
) {

    fun execute(device: Device): LiveData<PulseData> {
        return bluetoothRepository.connectDevice(device)
    }

}