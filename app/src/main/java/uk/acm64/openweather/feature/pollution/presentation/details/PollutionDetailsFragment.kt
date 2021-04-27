package uk.acm64.openweather.feature.pollution.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.acm64.R
import uk.acm64.databinding.FragmentPollutionDetailsBinding
import uk.acm64.openweather.contract.AppBaseFragment
import uk.acm64.openweather.feature.pollution.domain.usecase.GetPollutionHistoryUseCase
import uk.acm64.openweather.feature.pollution.presentation.history.PollutionInfoUi

class PollutionDetailsFragment : AppBaseFragment() {

    companion object {
        const val DETAILS = "PollutionDetailsFragment_key_DETAILS"
    }

    override fun layoutId() = R.layout.fragment_pollution_details

    private var _binding: FragmentPollutionDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPollutionDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showInfo(pollutionDetailsUi: PollutionInfoUi) {
        binding.pollutionDetailsFragmentTitle.text = pollutionDetailsUi.date
        binding.pollutionDetailsFragmentCo.text = pollutionDetailsUi.co
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pollutionDetails = arguments?.getParcelable<PollutionInfoUi>(DETAILS)
        if (pollutionDetails != null) {
            showInfo(pollutionDetails)
        } else {
            handleFailure(GetPollutionHistoryUseCase.GetPollutionHistoryFailure)
        }
    }
}