package com.nikolam.feature_feed.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.databinding.ConfessionFeedItemBinding
import com.nikolam.feature_feed.databinding.SortByItemBinding
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import timber.log.Timber

class FeedAdapter(private val listener: AdapterView.OnItemSelectedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var sortByIndex = 0

    fun setSortBy(sortBy : Int){
        sortByIndex = sortBy
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0 // sort by
        } else {
            return 1 // confessions
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as SortByViewHolder).bind()
        } else {
            val data = data[position]
            try {
                (holder as ConfessionViewHolder).bind(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val itemBinding =
                    SortByItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SortByViewHolder(itemBinding, listener)
            }
            else -> {
                val itemBinding = ConfessionFeedItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ConfessionViewHolder(itemBinding)
            }
        }

    }

    override fun getItemCount() = data.size

    private val data = ArrayList<ConfessionDomainModel>()

    fun newData(newData: List<ConfessionDomainModel>) {
        if (data == newData)
            return
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
        Timber.d(newData.toString())
    }

    inner class ConfessionViewHolder(
        private val itemBinding: ConfessionFeedItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: ConfessionDomainModel) {
            itemBinding.apply {
                likeAmountTextView.text = data.likes.toString()
                dislikeAmountTextView.text = data.dislikes.toString()
                textPreviewTextView.text = data.text
            }
        }
    }

    inner class SortByViewHolder(
        private val itemBinding: SortByItemBinding,
        private val listener: AdapterView.OnItemSelectedListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            itemBinding.apply {
                ArrayAdapter.createFromResource(
                    itemBinding.root.context,
                    R.array.sort_by_list,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    itemBinding.sortBySpinner.adapter = adapter
                }
                itemBinding.sortBySpinner.onItemSelectedListener = listener
                sortBySpinner.setSelection(sortByIndex)
            }
        }
    }

}