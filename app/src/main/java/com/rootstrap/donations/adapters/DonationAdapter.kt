package com.rootstrap.donations.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rootstrap.donations.R
import com.rootstrap.donations.databinding.ModelDonationBinding
import com.rootstrap.donations.models.Donation
import androidx.core.content.ContextCompat.startActivity
import android.net.Uri
import androidx.databinding.DataBindingUtil


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
        items.clear()
        items.addAll(donations!!)
        notifyItemRangeInserted(0, items.size)
    }

    inner class DonationViewHolder(view: View, var modelDonationBinding: ModelDonationBinding? = null) : RecyclerView.ViewHolder(view) {

        init {
            modelDonationBinding = DataBindingUtil.bind<ModelDonationBinding>(view)!!
        }

        fun setupView(donation: Donation) {
            modelDonationBinding!!.donation = donation
            modelDonationBinding!!.user = donation.user
            modelDonationBinding!!.donationUserContactAction.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                val parseWhatsAppContact = modelDonationBinding!!.donationUserContact.context.getString(R.string.open_whatsapp, donation.user!!.phone.trim(), donation.title)
                intent.data = Uri.parse(parseWhatsAppContact)
                startActivity(modelDonationBinding!!.donationUserContact.context, intent, null)
            }
        }
    }
}
