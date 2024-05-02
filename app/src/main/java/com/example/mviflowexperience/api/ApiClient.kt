package com.example.mviflowexperience.api

import com.example.mviflowexperience.models.BandModel

interface ApiClient {
//    @GET("bands")
    suspend fun getBands(): List<BandModel>
}