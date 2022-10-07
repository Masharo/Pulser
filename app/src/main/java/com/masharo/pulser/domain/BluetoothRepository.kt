package com.masharo.pulser.domain

import android.bluetooth.BluetoothDevice
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device

interface BluetoothRepository {

    fun getListBondedDevices(): List<BluetoothDevice>

    fun deviceHaveBluetooth(): Boolean

    fun deviceHavePermission(): Boolean

    fun isActiveBluetooth(): Boolean

    fun enableBluetooth()

    fun disableBluetooth()

    suspend fun connectDevice(device: Device): PulseData

}