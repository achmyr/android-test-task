package uk.acm64.openweather.feature.pollution.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uk.acm64.R
import uk.acm64.core.extension.*
import uk.acm64.databinding.FragmentHistoryBinding
import uk.acm64.openweather.contract.AppBaseFragment
import uk.acm64.openweather.feature.pollution.presentation.details.PollutionDetailsFragment

class PollutionHistoryFragment : AppBaseFragment() {
    override fun layoutId() = R.layout.fragment_history

    private lateinit var pollutionHistoryViewModel: PollutionHistoryViewModel
    private lateinit var historyListAdapter: HistoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        appComponent.inject(this)
        pollutionHistoryViewModel = viewModel(viewModelFactory) {
            observe(state, ::renderSearchResults)
            failure(failure, ::handleFailure)
        }
        historyListAdapter = HistoryListAdapter()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

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
        binding.listView.adapter = historyListAdapter
        historyListAdapter.itemClickListener = { info ->
            Bundle().apply {
                putParcelable(PollutionDetailsFragment.DETAILS, info)
            }.also {
                findNavController().navigate(
                    R.id.action_pollutionHistoryFragment_to_pollutionDetailsFragment,
                    it
                )
            }
        }
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
        historyListAdapter.pollutionInfoUi = data
    }

    private fun showEmpty() {
        binding.historyProgress.invisible()
        binding.historyEmpty.visible()
        binding.dataView.invisible()
    }

}