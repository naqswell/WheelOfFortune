package org.naqswell.whoff

import android.app.Application
import org.naqswell.whoff.di.KoinInitializer

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}