package com.example.mviflowexperience

sealed class DashboardIntent {
    object FetchBand: DashboardIntent()
    object ValidateBand: DashboardIntent()
    object DeleteBand: DashboardIntent()
}