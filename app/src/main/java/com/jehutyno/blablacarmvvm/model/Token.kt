package com.jehutyno.blablacarmvvm.model

data class Token(
    val access_token: String,
    val token_type: String,
    val issued_at: Long,
    val expires_in: Long,
    val scopes: List<String>
)