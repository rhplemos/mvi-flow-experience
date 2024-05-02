package com.example.mviflowexperience.api

import com.example.mviflowexperience.models.BandModel

class ApiHelperImpl(private val apiClient: ApiClient): ApiHelper {
    override suspend fun getBooks(): List<BandModel> {
        return apiClient.getBooks()
    }
}