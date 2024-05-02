package com.example.mviflowexperience

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by inject()
    private fun observeLiveData(){
        lifecycleScope.launch {
            viewModel.state.collect {
                when(it){
                    is MainViewModel.DashboardState.Idle -> {
                        Log.d("Worked", "Idle")
                    }
                    is MainViewModel.DashboardState.Loading -> {
                        handleProgressbar(View.VISIBLE)
                        Log.d("Worked", "Loading")
                    }
                    is MainViewModel.DashboardState.Success -> {
                        handleProgressbar(View.GONE)
                        Toast.makeText(applicationContext, "Bands ${it.bands.size}", Toast.LENGTH_LONG).show()
                    }
                    is MainViewModel.DashboardState.Error -> {
                        handleProgressbar(View.GONE)
                        Log.d("Worked", "Error")
                    }
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeLiveData()
        lifecycleScope.launch{
            viewModel.userIntent.send(DashboardIntent.FetchBand)
        }
    }
    fun handleProgressbar(viewVisibillity: Int){
        findViewById<ProgressBar>(com.google.android.material.R.id.accelerate).visibility = viewVisibillity
    }
}