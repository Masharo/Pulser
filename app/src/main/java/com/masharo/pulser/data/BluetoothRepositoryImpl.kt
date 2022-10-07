package com.masharo.pulser.data

import android.bluetooth.BluetoothDevice
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.domain.BluetoothRepository
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device
import java.io.InputStream

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

    override suspend fun connectDevice(device: Device): PulseData {
        return PulseData(
            bluetoothService.connect(device)
        )
    }

}