package com.masharo.pulser.presentation.model

import android.bluetooth.BluetoothDevice

data class Device(
    val name: String,
    val mac: String,
    val isSelect: Boolean = false
)

fun BluetoothDevice.toDevice() = Device(
    name = name,
    mac = address
)