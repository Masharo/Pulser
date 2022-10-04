package com.masharo.pulser.presentation.di

import com.masharo.pulser.data.BluetoothRepositoryImpl
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.data.bluetoothService.BluetoothServiceImpl
import com.masharo.pulser.domain.BluetoothRepository
import org.koin.dsl.module

val dataModule = module {

    single<BluetoothService> {
        BluetoothServiceImpl(
            context = get()
        )
    }

    single<BluetoothRepository> {
        BluetoothRepositoryImpl(
            bluetoothService = get()
        )
    }

}