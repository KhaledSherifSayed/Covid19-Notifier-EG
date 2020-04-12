package com.meslmawy.covid19_notifier_eg.model

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "response")
    val historyDataList: List<ResponseItem>
)