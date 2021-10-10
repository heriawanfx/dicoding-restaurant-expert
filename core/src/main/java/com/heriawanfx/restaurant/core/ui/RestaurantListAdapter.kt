package com.heriawanfx.restaurant.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heriawanfx.restaurant.core.R
import com.heriawanfx.restaurant.core.databinding.ItemGridRestaurantBinding
import com.heriawanfx.restaurant.core.domain.model.Restaurant

class RestaurantListAdapter(private val listener: Listener) : ListAdapter<Restaurant, RestaurantListAdapter.RestaurantGridViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantGridViewHolder {
        val binding = ItemGridRestaurantBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return RestaurantGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantGridViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class RestaurantGridViewHolder(private val binding: ItemGridRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant){
            with(binding){
                txtTitle.text = restaurant.name
                txtSubtitle.text = restaurant.description
                Glide.with(imgPicture.context)
                    .load(restaurant.getPictureUrl())
                    .placeholder(R.drawable.bg_placeholder)
                    .error(R.drawable.bg_error)
                    .into(imgPicture)
                root.setOnClickListener {
                    listener.onItemClick(restaurant)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface Listener {
        fun onItemClick(item: Restaurant)
    }

}