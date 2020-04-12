package com.meslmawy.covid19_notifier_eg.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meslmawy.covid19_notifier_eg.model.ApiResponse
import com.meslmawy.covid19_notifier_eg.repository.CovidEgyptRepository
import com.meslmawy.covid19_notifier_eg.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainViewModel(private val repository: CovidEgyptRepository) : ViewModel() {

    private val _covidLiveData = MutableLiveData<State<ApiResponse>>()

    val covidLiveData: LiveData<State<ApiResponse>>
        get() = _covidLiveData

    fun getData() {
        viewModelScope.launch {
            repository.getData().collect {
                _covidLiveData.value = it
            }
        }
    }
}