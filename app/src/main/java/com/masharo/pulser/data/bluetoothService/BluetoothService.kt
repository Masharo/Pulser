package com.masharo.pulser.data.bluetoothService

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device
import java.io.BufferedInputStream
import java.io.InputStream

interface BluetoothService {

    fun getDevices(): List<BluetoothDevice>

    fun haveBluetooth(): Boolean

    fun havePermission(): Boolean

    fun isActiveBluetooth(): Boolean

    fun disableBluetooth()

    fun enableBluetooth()

    fun connect(device: Device): Boolean

    fun getData(): InputStream?

}