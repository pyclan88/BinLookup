package com.example.binlist.ui.bincheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.binlist.R
import com.example.binlist.databinding.FragmentBinCheckBinding
import com.example.binlist.domain.state.CardInfoState
import com.example.binlist.domain.state.CardInfoState.*
import com.example.binlist.util.BindingFragment
import com.example.binlist.util.invisible
import com.example.binlist.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinCheckFragment : BindingFragment<FragmentBinCheckBinding>() {

    private val viewModel: BinCheckViewModel by viewModel()

    private lateinit var binCode: String
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBinCheckBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { render(it) }

        setListeners()
    }

    private fun render(state: CardInfoState) {
        when (state) {
            is Loading -> showLoading()
            is Content -> showContent(state)
            is NoResult -> showError(resources.getString(R.string.not_found_error))
            is Error -> showError(resources.getString(R.string.server_error))
            is NoInternet -> showError(resources.getString(R.string.no_inernet))
            is RateLimited -> showError(resources.getString(R.string.limit_warning))
        }
    }

    private fun showLoading() {
        binding.tvErrorPlaceholder.invisible()
        binding.group.invisible()

        binding.pb.visible()
    }

    private fun showContent(state: Content) {
        binding.tvErrorPlaceholder.invisible()
        binding.pb.invisible()

        val cardInfo = state.cardInfo

        setTextWithStyle(binding.tvSchemeValue, cardInfo.scheme)
        setTextWithStyle(binding.tvCountryNameValue, cardInfo.countryName)

        handleCoordinates(cardInfo.latitude, cardInfo.longitude, binding.tvCoordinatesValue)

        setTextWithStyle(binding.tvUrlValue, cardInfo.url)
        setTextWithStyle(binding.tvCityValue, cardInfo.city)
        setTextWithStyle(binding.tvPhoneValue, cardInfo.phone)

        binding.group.visible()
        viewModel.addCardToHistory(state.cardInfo.copy(bin = binCode))
    }

    private fun handleCoordinates(
        methodLatitude: Double?,
        methodLongitude: Double?,
        tvCoordinatesValue: TextView
    ) {
        var coordinates: String? = null
        if (methodLatitude != null && methodLongitude != null) {
            latitude = methodLatitude
            longitude = methodLongitude
            tvCoordinatesValue.isClickable = true
            coordinates = "$methodLatitude,$methodLongitude"
        }
        setTextWithStyle(tvCoordinatesValue, coordinates)
    }

    private fun setTextWithStyle(
        textView: TextView,
        value: String?,
    ) {
        textView.text = value ?: resources.getString(R.string.no_data)
    }

    private fun showError(errorText: String) {
        binding.pb.invisible()
        binding.group.invisible()

        binding.tvErrorPlaceholder.text = errorText
        binding.tvErrorPlaceholder.visible()
    }

    private fun setListeners() {
        binding.bCheck.setOnClickListener {
            binCode = binding.etBin.text.toString()
            viewModel.requestToServer(binCode)
        }

        binding.tvCoordinatesValue.setOnClickListener {
            if (latitude != null && longitude != null) {
                viewModel.onCoordinatesClicked(latitude!!, longitude!!)
            }
        }
    }

    companion object {
        fun newInstance() = BinCheckFragment()
    }
}