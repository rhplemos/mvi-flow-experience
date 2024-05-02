package com.example.mviflowexperience.api

import com.example.mviflowexperience.models.BandModel

interface ApiHelper {
    suspend fun getBooks(): List<BandModel>
}