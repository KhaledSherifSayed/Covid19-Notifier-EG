package com.meslmawy.covid19_notifier_eg.api

import com.meslmawy.covid19_notifier_eg.Constants.XRAPIDAPIKEY
import com.meslmawy.covid19_notifier_eg.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Covid19EgyptApiService {


    @Headers(XRAPIDAPIKEY)
    @GET("/history?country=egypt")
    suspend fun getHistoryData(): Response<ApiResponse>

    companion object {
        const val BASE_URL = " https://covid-193.p.rapidapi.com"
    }
}