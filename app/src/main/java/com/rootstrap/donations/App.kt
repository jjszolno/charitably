package com.rootstrap.donations

import android.app.Application
import android.content.Context
import com.squareup.otto.Bus

val bus: Bus by lazy {
    App.bus!!
}

class App : Application() {

    companion object {
        var bus: Bus? = null
    }

    override fun onCreate() {
        bus = Bus()
        super.onCreate()
    }
}
