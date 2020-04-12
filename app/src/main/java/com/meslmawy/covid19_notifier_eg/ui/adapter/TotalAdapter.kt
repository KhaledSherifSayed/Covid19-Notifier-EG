package com.meslmawy.covid19_notifier_eg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meslmawy.covid19_notifier_eg.databinding.ItemTotalBinding
import com.meslmawy.covid19_notifier_eg.model.ResponseItem

class TotalAdapter : ListAdapter<ResponseItem, TotalAdapter.TotalViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseItem>() {
            override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean =
                oldItem.day == newItem.day

            override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean =
                oldItem == newItem

        }
    }

    class TotalViewHolder(val binding: ItemTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(details: ResponseItem) {
            binding.response = details
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TotalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTotalBinding.inflate(layoutInflater, parent, false)
                return TotalViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalViewHolder {
        return TotalViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) {
        holder.binding.also {
            holder.bind(getItem(position))
        }
    }
}