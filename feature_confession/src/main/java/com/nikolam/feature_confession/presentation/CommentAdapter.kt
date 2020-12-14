package com.nikolam.feature_confession.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.feature_confession.databinding.CommentItemBinding
import com.nikolam.feature_confession.domain.models.CommentDomainModel

class CommentAdapter() :
        RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {

        val data = data[position]
        try {
            holder.bind(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentViewHolder {
        val itemBinding =
                CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(itemBinding)
    }

    override fun getItemCount() = data.size

    private val data = ArrayList<CommentDomainModel>()

    fun newData(newData: List<CommentDomainModel>) {
        if (data == newData)
            return
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(
            private val itemBinding: CommentItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: CommentDomainModel) {
            itemBinding.apply {
                likeAmountTextView.text = data.likes.toString()
                dislikeAmountTextView.text = data.dislikes.toString()
                commentTextTextView.text = data.text
            }
        }
    }
}