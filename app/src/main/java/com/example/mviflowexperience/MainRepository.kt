package com.example.mviflowexperience

import com.example.mviflowexperience.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getBands() = apiHelper.getBands()
}