package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.BluetoothRepository

class DeviceValidateUseCase(private val bluetoothRepository: BluetoothRepository) {

    fun execute() =
        bluetoothRepository.deviceHaveBluetooth()
        &&
        bluetoothRepository.deviceHavePermission()

}