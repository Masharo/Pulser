package com.masharo.pulser.presentation.app

import android.app.Application
import com.masharo.pulser.presentation.di.appModule
import com.masharo.pulser.presentation.di.dataModule
import com.masharo.pulser.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }

}