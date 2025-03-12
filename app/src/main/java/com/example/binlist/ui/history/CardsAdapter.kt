package com.example.binlist.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binlist.databinding.CardItemBinding
import com.example.binlist.domain.model.CardInfo

class CardsAdapter() :
    RecyclerView.Adapter<CardsViewHolder>() {

    var cards = ArrayList<CardInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return CardsViewHolder(CardItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }
}