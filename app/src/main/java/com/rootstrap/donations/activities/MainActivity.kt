package com.rootstrap.donations.activities

import android.os.Bundle
import com.rootstrap.donations.R
import com.rootstrap.donations.fragments.FragmentDonations
import com.rootstrap.donations.utils.addFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(FragmentDonations(), R.id.main_content)
    }
}
