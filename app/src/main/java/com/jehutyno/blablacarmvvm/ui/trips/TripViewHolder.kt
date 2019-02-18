package com.jehutyno.blablacarmvvm.ui.trips

import android.support.v7.widget.RecyclerView
import com.jehutyno.blablacarmvvm.databinding.TripListItemBinding
import com.jehutyno.blablacarmvvm.model.Trip

class TripViewHolder(private val binding: TripListItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val viewModel = TripViewModel()

    fun bind(trip: TripItem) {
        viewModel.bind(trip)
        binding.viewModel = viewModel
    }

//    val departureTime = view.departureTime
//    val locations = view.locations
//    val price = view.price
//    val userName = view.userName
//    val userPicture = view.userPicture

}