package com.masharo.pulser.domain

import android.bluetooth.BluetoothDevice

interface BluetoothRepository {

    fun getListBondedDevices(): List<BluetoothDevice>

    fun deviceHaveBluetooth(): Boolean

    fun deviceHavePermission(): Boolean

}