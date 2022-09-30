package com.example.acronymmeaningapp.presentation.screens.dashboard_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymmeaningapp.databinding.AcronymMeaningListItemBinding
import com.example.acronymmeaningapp.domain.models.LongForm

class AcronymMeaningsRVAdapter :
    ListAdapter<LongForm, AcronymMeaningsRVAdapter.AcronymMeaningViewHolder>(DiffCallback()) {

    class AcronymMeaningViewHolder(private val binding: AcronymMeaningListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(acronymMeaning: LongForm) {
            binding.longForm = acronymMeaning
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymMeaningViewHolder {
        val binding = AcronymMeaningListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AcronymMeaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcronymMeaningViewHolder, position: Int) {
        val acronymMeaning = getItem(position)
        holder.bind(acronymMeaning)
    }

    private class DiffCallback : DiffUtil.ItemCallback<LongForm>() {
        override fun areItemsTheSame(oldItem: LongForm, newItem: LongForm): Boolean {
            return oldItem.lf == newItem.lf
        }

        override fun areContentsTheSame(oldItem: LongForm, newItem: LongForm): Boolean {
            return oldItem.lf == newItem.lf
        }
    }
}
