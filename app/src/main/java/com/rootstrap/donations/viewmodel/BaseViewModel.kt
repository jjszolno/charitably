package com.rootstrap.donations.viewmodel

import androidx.lifecycle.ViewModel
import com.rootstrap.donations.bus

open class BaseViewModel : ViewModel() {
    init {
        bus.register(this)
    }

    override fun onCleared() {
        bus.unregister(this)
    }
}