package com.rootstrap.donations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rootstrap.donations.R
import com.rootstrap.donations.databinding.ModelDonationBinding
import com.rootstrap.donations.models.Donation

class DonationAdapter(var items: ArrayList<Donation> = ArrayList()) : RecyclerView.Adapter<DonationAdapter.DonationViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): DonationViewHolder {
        return DonationViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.model_donation, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: DonationViewHolder, position: Int) {
        viewHolder.setupView(items[position])
    }

    fun addItems(donations: ArrayList<Donation>? = ArrayList()) {
        items.addAll(donations!!)
        notifyItemRangeInserted(0, items.size)
    }

    inner class DonationViewHolder(view: View, var modelDonationBinding: ModelDonationBinding? = null) : RecyclerView.ViewHolder(view) {

        init {
            modelDonationBinding = DataBindingUtil.bind<ModelDonationBinding>(view)!!
        }

        fun setupView(donation: Donation) {
            modelDonationBinding!!.donation = donation
            modelDonationBinding!!.showContact = false
            modelDonationBinding!!.actionContactInfo.setOnClickListener {

            }
        }
    }
}
