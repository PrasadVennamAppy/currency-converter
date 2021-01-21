package com.linpack.converter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linpack.converter.helper.Resource
import com.linpack.converter.helper.SingleLiveEvent
import com.linpack.converter.model.ApiResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepo: MainRepo) : ViewModel() {


    //cached
    private val _data = SingleLiveEvent<Resource<ApiResponse>>()


    //public
    val data = _data
    val convertedRate = MutableLiveData<Double>()


    //Public function to get the result of conversion
    fun getConvertedData(access_key: String, from: String, to: String, amount: Double) {
        viewModelScope.launch {
            mainRepo.getConvertedData(access_key, from, to, amount).collect {
                data.value = it
            }
        }
    }


}