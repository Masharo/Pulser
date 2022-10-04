package com.masharo.pulser.data

import android.bluetooth.BluetoothDevice
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.domain.BluetoothRepository

class BluetoothRepositoryImpl(bluetoothService: BluetoothService): BluetoothRepository {

    override fun getListBondedDevices(): List<BluetoothDevice> {
        TODO("Not yet implemented")
    }

    override fun deviceHaveBluetooth(): Boolean {
        TODO("Not yet implemented")
    }

    override fun deviceHavePermission(): Boolean {
        TODO("Not yet implemented")
    }

}