package com.example.mviflowexperience.api

import com.example.mviflowexperience.models.BandModel

class ApiHelperImpl(private val apiClient: ApiClient): ApiHelper {
    override suspend fun getBands(): List<BandModel> {
        return apiClient.getBands()
    }
}