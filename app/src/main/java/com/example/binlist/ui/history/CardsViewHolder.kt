package com.example.binlist.ui.history

import androidx.recyclerview.widget.RecyclerView
import com.example.binlist.R
import com.example.binlist.databinding.CardItemBinding
import com.example.binlist.domain.model.CardInfo

class CardsViewHolder(private val binding: CardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(card: CardInfo) = with(binding) {
        val resources = itemView.context.resources
        tvDateValue.text = card.addedAt
        tvBinValue.text = card.bin
        tvSchemeValue.text = card.scheme ?: resources.getString(R.string.no_data)
        tvCountryNameValue.text = card.countryName ?: resources.getString(R.string.no_data)
        tvLatitudeValue.text = card.latitude?.toString() ?: resources.getString(R.string.no_data)
        tvLongitudeValue.text = card.longitude?.toString() ?: resources.getString(R.string.no_data)
        tvCityValue.text = card.city ?: resources.getString(R.string.no_data)
        tvUrlValue.text = card.url ?: resources.getString(R.string.no_data)
        tvPhoneValue.text = card.url ?: resources.getString(R.string.no_data)
    }
}