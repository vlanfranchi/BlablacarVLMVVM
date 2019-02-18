package com.jehutyno.blablacarmvvm.ui.trips

data class TripItem(
    val departureTime: String,
    val locations: List<String>,
    val price: String,
    val userName: String,
    val userPictureUrl: String
)