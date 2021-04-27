package uk.acm64.openweather.feature.pollution.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.acm64.core.extension.*
import uk.acm64.R
import uk.acm64.databinding.FragmentHistoryBinding
import uk.acm64.openweather.contract.AppBaseFragment

class PollutionHistoryFragment : AppBaseFragment() {
    override fun layoutId() = R.layout.fragment_history

    private lateinit var pollutionHistoryViewModel: PollutionHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        appComponent.inject(this)
        pollutionHistoryViewModel = viewModel(viewModelFactory) {
            observe(state, ::renderSearchResults)
            failure(failure, ::handleFailure)
        }
    }

    private var _binding: FragmentHistoryBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pollutionHistoryViewModel.loadData()
    }



    private fun renderSearchResults(pollutionHistoryViewState: PollutionHistoryViewState?) {
        when (pollutionHistoryViewState) {
            is PollutionHistoryViewState.Empty -> showEmpty()
            is PollutionHistoryViewState.Loading -> showLoading()
            is PollutionHistoryViewState.Available -> show(pollutionHistoryViewState.data)
        }
    }

    private fun showLoading() {
        binding.historyProgress.visible()
        binding.historyEmpty.invisible()
        binding.dataView.invisible()

    }

    private fun show(data: List<PollutionInfoUi>) {
        binding.historyProgress.invisible()
        binding.historyEmpty.invisible()
        binding.dataView.visible()
    }

    private fun showEmpty() {
        binding.historyProgress.invisible()
        binding.historyEmpty.visible()
        binding.dataView.invisible()
    }

}