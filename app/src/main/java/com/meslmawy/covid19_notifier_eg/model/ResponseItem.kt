package com.meslmawy.covid19_notifier_eg.model

import com.squareup.moshi.Json

data class ResponseItem(
    val country: String = "Egypt",
    val cases : Case,
    val deaths : Death,
    val day : String = "0",
    @Json(name = "time")
    val lastUpdatedTime: String? = null
)