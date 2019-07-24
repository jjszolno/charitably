package com.rootstrap.donations.fragments

import androidx.fragment.app.Fragment
import com.rootstrap.donations.bus

abstract class BaseFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)
    }
}
