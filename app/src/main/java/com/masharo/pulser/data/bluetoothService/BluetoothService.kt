package com.masharo.pulser.data.bluetoothService

import android.bluetooth.BluetoothDevice
import com.masharo.pulser.presentation.model.Device
import java.io.BufferedInputStream

interface BluetoothService {

    fun getDevices(): List<BluetoothDevice>

    fun haveBluetooth(): Boolean

    fun havePermission(): Boolean

    fun isActiveBluetooth(): Boolean

    fun disableBluetooth()

    fun enableBluetooth()

    suspend fun connect(device: Device): BufferedInputStream

}