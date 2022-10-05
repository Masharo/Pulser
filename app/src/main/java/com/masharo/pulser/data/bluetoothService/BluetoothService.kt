package com.masharo.pulser.data.bluetoothService

import android.bluetooth.BluetoothDevice

interface BluetoothService {

    fun getDevices(): List<BluetoothDevice>

    fun haveBluetooth(): Boolean

    fun havePermission(): Boolean

    fun isActiveBluetooth(): Boolean

    fun disableBluetooth()

    fun enableBluetooth()

}