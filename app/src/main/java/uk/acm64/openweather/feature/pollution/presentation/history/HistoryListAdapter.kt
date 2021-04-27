package uk.acm64.openweather.feature.pollution.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.acm64.databinding.ListItemBinding
import kotlin.properties.Delegates

class HistoryListAdapter : RecyclerView.Adapter<HistoryListAdapter.ListViewHolder>() {

    var itemClickListener: ((pollutionInfoUi: PollutionInfoUi) -> Unit)? = null
    var pollutionInfoUi: List<PollutionInfoUi> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pollutionInfoUi.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(pollutionInfoUi[position])
    }

    inner class ListViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(infoUi: PollutionInfoUi) {
            itemView.apply {
                binding.listItemText.text = infoUi.date
                setOnClickListener {
                    itemClickListener?.invoke(infoUi)
                }
            }
        }
    }
}