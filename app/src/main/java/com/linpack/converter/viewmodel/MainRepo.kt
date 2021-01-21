package com.linpack.converter.viewmodel

import com.linpack.converter.helper.Resource
import com.linpack.converter.model.ApiResponse
import com.linpack.converter.network.ApiDataSource
import com.linpack.converter.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepo @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {

    //Using coroutines flow to get the response from
    suspend fun getConvertedData(
        access_key: String,
        from: String,
        to: String,
        amount: Double
    ): Flow<Resource<ApiResponse>> {
        return flow {
            emit(safeApiCall { apiDataSource.getConvertedRate(access_key, from, to, amount) })
        }.flowOn(Dispatchers.IO)
    }


}