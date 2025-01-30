package com.vk.directop.memoriesjournal

import android.app.Application
import com.vk.directop.memoriesjournal.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EchoApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EchoApp)
            androidLogger()

            modules(appModule)
        }
    }
}