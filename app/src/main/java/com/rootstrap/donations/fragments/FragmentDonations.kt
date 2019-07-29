package com.rootstrap.donations.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.databinding.FragmentDonationsBinding
import com.rootstrap.donations.viewmodel.fragmentDonations.FragmentDonationsVM

class FragmentDonations(
    var fragmentDonationBinding: FragmentDonationsBinding? = null,
    var viewModel: FragmentDonationsVM? = null) : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_donations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDonationBinding = DataBindingUtil.bind(view)
        viewModel = FragmentDonationsVM(fragmentDonationBinding!!, activity as BaseActivity)
    }
}
