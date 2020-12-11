package com.nikolam.feature_feed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.databinding.ConfessionFeedItemBinding
import com.nikolam.feature_feed.databinding.FeedFragmentBinding
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import timber.log.Timber

class FeedAdapter(

) : RecyclerView.Adapter<FeedAdapter.ConfessionViewHolder>() {

    override fun onBindViewHolder(holder: ConfessionViewHolder, position: Int) {
        val data = data[position]
        try {
            holder.bind(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfessionViewHolder {
        val itemBinding = ConfessionFeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConfessionViewHolder(itemBinding)
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

}