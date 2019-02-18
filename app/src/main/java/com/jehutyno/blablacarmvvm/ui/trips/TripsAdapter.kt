package com.jehutyno.blablacarmvvm.ui.trips

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jehutyno.blablacarmvvm.R
import com.jehutyno.blablacarmvvm.databinding.TripListItemBinding

class TripsAdapter(private val context: Context): RecyclerView.Adapter<TripViewHolder>() {

    private var items: List<TripItem> = listOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding: TripListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.trip_list_item, parent, false)
        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(items: List<TripItem>?) {
        items?.let {
            this.items = items
            notifyDataSetChanged()
        }
    }

}
