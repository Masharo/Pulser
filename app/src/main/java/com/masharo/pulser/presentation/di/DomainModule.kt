package com.masharo.pulser.presentation.di

import com.masharo.pulser.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {

    factory<DeviceValidateUseCase> {
        DeviceValidateUseCase(
            bluetoothRepository = get()
        )
    }

    factory<GetBondedDevicesUseCase> {
        GetBondedDevicesUseCase(
            bluetoothRepository = get()
        )
    }

    factory<IsActiveBluetoothUseCase> {
        IsActiveBluetoothUseCase(
            bluetoothRepository = get()
        )
    }

    factory<EnableBluetoothUseCase> {
        EnableBluetoothUseCase(
            bluetoothRepository = get()
        )
    }

    factory<DisableBluetoothUseCase> {
        DisableBluetoothUseCase(
            bluetoothRepository = get()
        )
    }

    factory<GetDataDeviceUseCase> {
        GetDataDeviceUseCase(
            bluetoothRepository = get(),
            dispatcher = Dispatchers.IO
        )
    }

}