package com.masharo.pulser.presentation.di

import com.masharo.pulser.data.repository.BluetoothRepositoryImpl
import com.masharo.pulser.data.repository.DatabaseRepositoryImpl
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.data.bluetoothService.BluetoothServiceImpl
import com.masharo.pulser.data.database.PulserDatabase
import com.masharo.pulser.data.database.PulserStorage
import com.masharo.pulser.data.database.PulserStorageImpl
import com.masharo.pulser.data.repository.WorkerRepositoryImpl
import com.masharo.pulser.domain.BluetoothRepository
import com.masharo.pulser.domain.DatabaseRepository
import com.masharo.pulser.domain.WorkerRepository
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

    single<WorkerRepository> {
        WorkerRepositoryImpl(
            context = get()
        )
    }

}