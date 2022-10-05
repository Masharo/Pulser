package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.BluetoothRepository

class IsActiveBluetoothUseCase(
    private val bluetoothRepository: BluetoothRepository
) {

    fun execute(): Boolean {
        return bluetoothRepository.isActiveBluetooth()
    }

}