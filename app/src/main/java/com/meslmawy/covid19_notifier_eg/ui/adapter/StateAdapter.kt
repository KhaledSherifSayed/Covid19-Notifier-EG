package com.meslmawy.covid19_notifier_eg.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meslmawy.covid19_notifier_eg.R
import com.meslmawy.covid19_notifier_eg.databinding.ItemStateBinding
import com.meslmawy.covid19_notifier_eg.databinding.ItemTotalBinding
import com.meslmawy.covid19_notifier_eg.model.ResponseItem
import com.meslmawy.covid19_notifier_eg.utils.getPeriod
import java.text.SimpleDateFormat

class StateAdapter : ListAdapter<ResponseItem, StateAdapter.StateViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseItem>() {
            override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean =
                oldItem.day == newItem.day

            override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean =
                oldItem == newItem

        }
    }

    class StateViewHolder(val binding: ItemStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(details: ResponseItem) {
            binding.response = details
            binding.textState.text = details.country
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): StateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemStateBinding.inflate(layoutInflater, parent, false)
                return StateViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.binding.also {
            holder.bind(getItem(position))
        }
    }
}