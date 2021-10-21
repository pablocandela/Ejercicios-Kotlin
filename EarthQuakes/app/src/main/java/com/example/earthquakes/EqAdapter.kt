package com.example.earthquakes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.databinding.EqListItemBinding

class EqAdapter: ListAdapter<EarthQuake, EqAdapter.EqViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<EarthQuake>(){
        override fun areItemsTheSame(oldItem: EarthQuake, newItem: EarthQuake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EarthQuake, newItem: EarthQuake): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onIntemClickListener: (EarthQuake) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqAdapter.EqViewHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EqAdapter.EqViewHolder, position: Int) {
        val eartquake = getItem(position)
        holder.bind(eartquake)
    }

    inner class EqViewHolder(private val binding: EqListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(earthquake: EarthQuake){
            binding.eqMagnitudeText.text = earthquake.magnitude.toString()
            binding.eqPlaceText.text = earthquake.place
            binding.root.setOnClickListener {
                onIntemClickListener(earthquake)
            }
            binding.executePendingBindings()
        }
    }


}