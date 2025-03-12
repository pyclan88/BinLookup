package com.example.binlist.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.binlist.databinding.FragmentHistoryBinding
import com.example.binlist.domain.model.CardInfo
import com.example.binlist.domain.state.HistoryCardState
import com.example.binlist.util.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BindingFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()
    private var cardsAdapter: CardsAdapter? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHistoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHistoryCards()

        cardsAdapter = CardsAdapter()
        binding.rv.adapter = cardsAdapter

        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: HistoryCardState) {
        when (state) {
            is HistoryCardState.Data -> showContent(state.cards)
            is HistoryCardState.Empty -> showEmpty()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(cards: List<CardInfo>) {
        cardsAdapter?.apply {
            this.cards.clear()
            this.cards.addAll(cards)
            notifyDataSetChanged()
        }
    }

    private fun showEmpty() {}


    companion object {
        fun newInstance() = HistoryFragment()
    }
}