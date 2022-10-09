package com.masharo.pulser.data.bluetoothService

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Context.BLUETOOTH_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.presentation.model.Device
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.*

class BluetoothServiceImpl(private val context: Context): BluetoothService {

    private val bluetoothService = context.getSystemService(BluetoothManager::class.java)
    private val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private var data = MutableLiveData<PulseData>()

    @SuppressLint("MissingPermission")
    override fun getDevices() = bluetoothService!!
        .adapter
        .bondedDevices
        .toList()

    override fun haveBluetooth() = bluetoothService
        ?.let { true }
        ?: false

    override fun havePermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    override fun isActiveBluetooth(): Boolean {
        return bluetoothService!!
            .adapter
            .isEnabled
    }

    override fun disableBluetooth() {
        bluetoothService!!
            .adapter
            .disable()
    }

    override fun enableBluetooth() {
        bluetoothService!!
            .adapter
            .enable()
    }

    override fun connect(device: Device): LiveData<PulseData> {
        bluetoothService!!
            .adapter
            .getRemoteDevice(device.mac)
            .createRfcommSocketToServiceRecord(uuid)
            .apply {
                connect()
                data = MutableLiveData(
                    PulseData(inputStream.buffered())
                )
                return data
            }
    }

}