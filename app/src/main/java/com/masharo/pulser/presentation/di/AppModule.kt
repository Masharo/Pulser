package com.masharo.pulser.presentation.di

import com.masharo.pulser.presentation.vm.ConnectDeviceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<ConnectDeviceViewModel>{
        ConnectDeviceViewModel(
            context = get(),
            deviceValidateUseCase = get(),
            isActiveBluetoothUseCase = get(),
            getBondedDevicesUseCase = get(),
            enableBluetoothUseCase = get(),
            disableBluetoothUseCase = get(),
            connectDeviceUseCase = get()
        )
    }

}