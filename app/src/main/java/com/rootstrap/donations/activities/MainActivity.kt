package com.rootstrap.donations.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rootstrap.donations.R
import com.rootstrap.donations.bus
import com.rootstrap.donations.databinding.ActivityMainBinding
import com.rootstrap.donations.fragments.FragmentDonations
import com.rootstrap.donations.utils.addFragment

class MainActivity : BaseActivity() {

    private var mainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        mainBinding!!.createDonation.setOnClickListener {
            startActivity(Intent(this, DonationActivity::class.java))
        }

        addFragment(FragmentDonations(), R.id.main_content)
    }
}
