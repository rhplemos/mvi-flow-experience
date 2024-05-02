package com.example.mviflowexperience

import androidx.lifecycle.ViewModel
import com.example.mviflowexperience.models.BandModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {
    val userIntent = Channel<DashboardIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<DashboardState>(DashboardState.Idle)
    val state: StateFlow<DashboardState>
        get() = _state


    sealed class DashboardState {
        object Idle : DashboardState()
        object Loading : DashboardState()
        data class Success(val bands: List<BandModel>) : DashboardState()
        data class Error(val error: String?) : DashboardState()
    }

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is DashboardIntent.FetchBand -> fetchBand()
                    is DashboardIntent.ValidateBand -> validateBand()
                    is DashboardIntent.DeleteBand -> deleteBand()
                }
            }
        }
    }

    private fun fetchBand() {
        viewModelScope.launch {
            _state.value = DashboardState.Loading
            _state.value = try {
                DashboardState.Success(repository.getBands())
            } catch (e: Exception) {
                DashboardState.Error(e.localizedMessage)
            }
        }
    }

    private fun validateBand() {

    }


    private fun deleteBand() {

    }

}