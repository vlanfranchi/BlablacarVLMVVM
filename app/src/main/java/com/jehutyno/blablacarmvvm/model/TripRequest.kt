package com.jehutyno.blablacarmvvm.model

data class TripRequest(
    val _format: String,
    val locale: String,
    val cur: String,
    val fn: String,
    val tn: String
)