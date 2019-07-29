package com.rootstrap.donations

import android.app.Application
import android.content.Context
import com.squareup.otto.Bus

val bus: Bus by lazy {
    App.bus!!
}

val appContext by lazy {
    App.appContext!!
}

class App : Application() {

    companion object {
        var bus: Bus? = null
        var appContext: Context? = null
    }

    override fun onCreate() {
        bus = Bus()
        appContext = applicationContext
        super.onCreate()
    }
}
