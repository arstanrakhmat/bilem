package com.example.bilemonline.application

import android.app.Application
import com.example.bilemonline.koin.retrofitModule
import com.example.bilemonline.koin.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(retrofitModule, viewModules)
            androidContext(this@MyApplication)
        }
    }
}