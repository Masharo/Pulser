package com.masharo.pulser.presentation.model

import android.bluetooth.BluetoothDevice
import java.util.*

data class Device(
    val name: String,
    val mac: String,
    val uuid: UUID,
    val isSelect: Boolean = false
)

fun BluetoothDevice.toDevice() = Device(
    name = name,
    mac = address,
    uuid = uuids[0].uuid
)