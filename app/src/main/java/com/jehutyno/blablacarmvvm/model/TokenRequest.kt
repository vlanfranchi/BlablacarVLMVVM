package com.jehutyno.blablacarmvvm.model

data class TokenRequest(
    val grant_type: String,
    val client_id: String,
    val client_secret: String
)