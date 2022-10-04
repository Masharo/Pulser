package com.masharo.pulser.presentation.di

import com.masharo.pulser.domain.usecase.DeviceValidateUseCase
import com.masharo.pulser.domain.usecase.GetBondedDevicesUseCase
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

}