package com.jucode.nutrisport

import android.app.Application
import com.jucode.nutrisport.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NutriSportApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NutriSportApplication)
            modules(appModule)
        }
    }
}
