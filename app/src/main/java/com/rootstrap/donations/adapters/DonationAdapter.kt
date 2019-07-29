package com.rootstrap.donations.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rootstrap.donations.R
import com.rootstrap.donations.appContext
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
        notifyItemRangeRemoved(0,items.size)
        items.clear()
        items.addAll(donations!!)
        notifyItemRangeInserted(0, items.size)
    }

    fun contactUser(phone: String, title: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val parseWhatsAppContact = appContext.getString(R.string.open_whatsapp, phone, title)
        intent.data = Uri.parse(parseWhatsAppContact)
        startActivity(appContext, intent, null)
    }

    open inner class DonationViewHolder(view: View, var modelDonationBinding: ModelDonationBinding? = null) : RecyclerView.ViewHolder(view) {

        init {
            modelDonationBinding = DataBindingUtil.bind(view)!!
            modelDonationBinding!!.adapter = this@DonationAdapter
        }

        fun setupView(donation: Donation) {
            modelDonationBinding!!.donation = donation
        }
    }
}
