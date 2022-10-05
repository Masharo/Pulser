package com.masharo.pulser.data

import android.bluetooth.BluetoothDevice
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.domain.BluetoothRepository

class BluetoothRepositoryImpl(
    private val bluetoothService: BluetoothService
): BluetoothRepository {

    override fun getListBondedDevices(): List<BluetoothDevice> {
        return bluetoothService.getDevices()
    }

    override fun deviceHaveBluetooth(): Boolean {
        return bluetoothService.haveBluetooth()
    }

    override fun deviceHavePermission(): Boolean {
        return bluetoothService.havePermission()
    }

    override fun isActiveBluetooth(): Boolean {
        return bluetoothService.isActiveBluetooth()
    }

    override fun enableBluetooth() {
        bluetoothService.enableBluetooth()
    }

    override fun disableBluetooth() {
        bluetoothService.disableBluetooth()
    }

}