package com.masharo.pulser.data.bluetoothService

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Context.BLUETOOTH_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat.checkSelfPermission

class BluetoothServiceImpl(private val context: Context): BluetoothService {


    @SuppressLint("MissingPermission")
    override fun getDevices() = context
        .getSystemService(BluetoothManager::class.java)!!
        .adapter
        .bondedDevices
        .toList()

    override fun haveBluetooth() = context
        .getSystemService(BLUETOOTH_SERVICE)
        ?.let { true }
        ?: false

    override fun havePermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
        } else {
            true
        }


}