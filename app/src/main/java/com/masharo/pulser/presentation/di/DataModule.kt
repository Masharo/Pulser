package com.masharo.pulser.presentation.di

import com.masharo.pulser.data.BluetoothRepositoryImpl
import com.masharo.pulser.data.DatabaseRepositoryImpl
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.data.bluetoothService.BluetoothServiceImpl
import com.masharo.pulser.data.database.PulserDatabase
import com.masharo.pulser.data.database.PulserStorage
import com.masharo.pulser.data.database.PulserStorageImpl
import com.masharo.pulser.domain.BluetoothRepository
import com.masharo.pulser.domain.DatabaseRepository
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

    single<PulserDatabase> {
        PulserDatabase.instance(
            context = get()
        )
    }

    single<PulserStorage> {
        PulserStorageImpl(
            db = get()
        )
    }

    single<DatabaseRepository> {
        DatabaseRepositoryImpl(
            storege = get()
        )
    }

}