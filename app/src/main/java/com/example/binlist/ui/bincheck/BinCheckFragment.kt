package com.example.binlist.ui.bincheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.binlist.R
import com.example.binlist.databinding.FragmentBinCheckBinding
import com.example.binlist.domain.state.CardInfoState
import com.example.binlist.util.BindingFragment
import com.example.binlist.util.invisible
import com.example.binlist.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinCheckFragment : BindingFragment<FragmentBinCheckBinding>() {

    private val viewModel: BinCheckViewModel by viewModel()

    private lateinit var binCode: String

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBinCheckBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: CardInfoState) {
        when (state) {
            is CardInfoState.Loading -> showLoading()
            is CardInfoState.Content -> showContent(state)
            is CardInfoState.NoResult -> showNoResult()
            is CardInfoState.Error -> showError()
            is CardInfoState.NoInternet -> showNoInternet()
            is CardInfoState.RateLimited -> showRateLimited()
        }
    }

    private fun showLoading() {
        binding.tvErrorPlaceholder.invisible()
        binding.group.invisible()

        binding.pb.visible()
    }

    private fun showContent(state: CardInfoState.Content) {
        binding.tvErrorPlaceholder.invisible()
        binding.pb.invisible()

        binding.group.visible()

        binding.tvSchemeValue.text = state.cardInfo.scheme ?: resources.getString(R.string.no_data)
        binding.tvCountryNameValue.text =
            state.cardInfo.countryName ?: resources.getString(R.string.no_data)
        binding.tvLatitudeValue.text =
            state.cardInfo.latitude?.toString() ?: resources.getString(R.string.no_data)
        binding.tvLongitudeValue.text =
            state.cardInfo.longitude?.toString() ?: resources.getString(R.string.no_data)
        binding.tvUrlValue.text = state.cardInfo.url ?: resources.getString(R.string.no_data)
        binding.tvPhoneValue.text = state.cardInfo.phone ?: resources.getString(R.string.no_data)
        binding.tvCityValue.text = state.cardInfo.city ?: resources.getString(R.string.no_data)

        viewModel.addCardToHistory(state.cardInfo.copy(bin = binCode))
    }

    private fun showNoResult() {
        binding.pb.invisible()
        binding.group.invisible()

        binding.tvErrorPlaceholder.text = resources.getString(R.string.not_found_error)
        binding.tvErrorPlaceholder.visible()
    }

    private fun showError() {
        binding.pb.invisible()
        binding.group.invisible()

        binding.tvErrorPlaceholder.text = resources.getString(R.string.server_error)
        binding.tvErrorPlaceholder.visible()
    }

    private fun showNoInternet() {
        binding.pb.invisible()
        binding.group.invisible()

        binding.tvErrorPlaceholder.text = resources.getString(R.string.no_inernet)
        binding.tvErrorPlaceholder.visible()
    }

    private fun showRateLimited() {
        binding.pb.invisible()
        binding.group.invisible()

        binding.tvErrorPlaceholder.text = resources.getString(R.string.limit_warning)
        binding.tvErrorPlaceholder.visible()
    }

    private fun setListeners() {
        binding.bCheck.setOnClickListener {
            binCode = binding.etBin.text.toString()
            viewModel.requestToServer(binCode)
        }
    }

    companion object {
        fun newInstance() = BinCheckFragment()
    }
}