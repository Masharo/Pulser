package com.masharo.pulser.presentation.vm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masharo.pulser.domain.usecase.*
import com.masharo.pulser.presentation.model.Device
import com.masharo.pulser.presentation.model.toDevice

class ConnectDeviceViewModel(
    private val context: Context,
    private val deviceValidateUseCase: DeviceValidateUseCase,
    private val isActiveBluetoothUseCase: IsActiveBluetoothUseCase,
    private val getBondedDevicesUseCase: GetBondedDevicesUseCase,
    private val enableBluetoothUseCase: EnableBluetoothUseCase,
    private val disableBluetoothUseCase: DisableBluetoothUseCase
): ViewModel() {

    private val deviceListLocal = MutableLiveData<List<Device>>()
    val deviceList: LiveData<List<Device>> = deviceListLocal

    fun testBluetooth() {
        Toast.makeText(
            context,
            if (deviceValidateUseCase.execute() && isActiveBluetoothUseCase.execute()) "ON"
            else "OFF",
            Toast.LENGTH_LONG).show()
    }

    fun getListBluetooth() {

        deviceListLocal.value = getBondedDevicesUseCase.execute().map { it.toDevice() }

//        Toast.makeText(context, getBondedDevicesUseCase.execute().toString(), Toast.LENGTH_LONG).show()

//        ContextCompat.getSystemService(context, BluetoothManager::class.java)!!.adapter.bondedDevices.let {
//            val device = it.toList()[1]
//            val connect = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
//            connect.connect()
//            val read = (connect.inputStream as InputStream).read()
//            Toast.makeText(context, read.toString(), Toast.LENGTH_SHORT).show()

    }

}