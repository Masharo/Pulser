package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.BluetoothRepository

class DisableBluetoothUseCase(
    private val bluetoothRepository: BluetoothRepository
) {

    fun execute() {
        bluetoothRepository.disableBluetooth()
    }

}