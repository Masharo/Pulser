package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.BluetoothRepository

class EnableBluetoothUseCase(
    private val bluetoothRepository: BluetoothRepository
) {

    fun execute() {
        bluetoothRepository.enableBluetooth()
    }

}