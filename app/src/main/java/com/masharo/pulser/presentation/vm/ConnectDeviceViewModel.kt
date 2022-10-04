package com.masharo.pulser.presentation.vm

import android.bluetooth.BluetoothManager
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import java.io.InputStream

class ConnectDeviceViewModel(
    private val context: Context
): ViewModel() {

    fun testBluetooth() {
        Toast.makeText(context, "Context here!!!", Toast.LENGTH_LONG).show()
    }

    fun test() {
        ContextCompat.getSystemService(context, BluetoothManager::class.java)!!.adapter.bondedDevices.let {
            val device = it.toList()[1]
            val connect = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
            connect.connect()
            val read = (connect.inputStream as InputStream).read()
            Toast.makeText(context, read.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}