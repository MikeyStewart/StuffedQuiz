package com.example.stuffedquiz.data

import com.squareup.moshi.Json

data class SessionTokenResponse(
    @Json(name = "response_code")
    val responseCode: Int,
    val token: String
)