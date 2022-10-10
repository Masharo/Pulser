package com.masharo.pulser.presentation.vm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masharo.pulser.domain.model.PulseData
import com.masharo.pulser.domain.usecase.*
import com.masharo.pulser.presentation.model.Device
import com.masharo.pulser.presentation.model.toDevice
import kotlinx.coroutines.launch

class ConnectDeviceViewModel(
    private val context: Context,
    private val deviceValidateUseCase: DeviceValidateUseCase,
    private val isActiveBluetoothUseCase: IsActiveBluetoothUseCase,
    private val getBondedDevicesUseCase: GetBondedDevicesUseCase,
    private val enableBluetoothUseCase: EnableBluetoothUseCase,
    private val disableBluetoothUseCase: DisableBluetoothUseCase,
    private val connectDeviceUseCase: ConnectDeviceUseCase,
    private val saveBluetoothDataToDatabaseUseCase: SaveBluetoothDataToDatabaseUseCase
): ViewModel() {

    private val deviceListLiveLocal = MutableLiveData<List<Device>>()
    val deviceListLive: LiveData<List<Device>> = deviceListLiveLocal

    fun testBluetooth() {
        Toast.makeText(
            context,
            if (deviceValidateUseCase.execute() && isActiveBluetoothUseCase.execute()) "ON"
            else "OFF",
            Toast.LENGTH_LONG).show()
    }

    fun connectToDevice(device: Device) {
        if (connectDeviceUseCase.execute(device)) {
            viewModelScope.launch {
                saveBluetoothDataToDatabaseUseCase.execute()
            }
        }
    }

    fun getListBluetooth() {

        deviceListLiveLocal.value = getBondedDevicesUseCase.execute().map { it.toDevice() }

//        Toast.makeText(context, getBondedDevicesUseCase.execute().toString(), Toast.LENGTH_LONG).show()

//        ContextCompat.getSystemService(context, BluetoothManager::class.java)!!.adapter.bondedDevices.let {
//            val device = it.toList()[1]
//            val connect = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
//            connect.connect()
//            val read = (connect.inputStream as InputStream).read()
//            Toast.makeText(context, read.toString(), Toast.LENGTH_SHORT).show()

    }

}