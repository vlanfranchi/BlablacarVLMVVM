package com.jehutyno.blablacarmvvm.model

data class Trip(
    val departure_date: String,
    val locations_to_display: List<String>,
    val price: Price,
    val user: User
)