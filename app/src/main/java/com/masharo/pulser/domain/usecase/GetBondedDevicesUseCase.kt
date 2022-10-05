package com.masharo.pulser.domain.usecase

import com.masharo.pulser.domain.BluetoothRepository

class GetBondedDevicesUseCase(private val bluetoothRepository: BluetoothRepository) {

    fun execute() =
        bluetoothRepository.getListBondedDevices()

}