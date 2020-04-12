package com.meslmawy.covid19_notifier_eg.repository

import NetworkBoundRepository
import com.meslmawy.covid19_notifier_eg.api.Covid19EgyptApiService
import com.meslmawy.covid19_notifier_eg.model.ApiResponse
import com.meslmawy.covid19_notifier_eg.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class CovidEgyptRepository(private val apiService: Covid19EgyptApiService) {

    fun getData(): Flow<State<ApiResponse>> {
        return object : NetworkBoundRepository<ApiResponse>() {
            override suspend fun fetchFromRemote(): Response<ApiResponse> = apiService.getHistoryData()
        }.asFlow().flowOn(Dispatchers.IO)
    }
}