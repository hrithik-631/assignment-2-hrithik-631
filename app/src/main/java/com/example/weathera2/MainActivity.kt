package com.example.weathera2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathera2.databinding.ActivityMainBinding

import com.example.weathera2.util.Resource
import com.example.weathera2.util.Status

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val adapter = ForecastAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.weatherData.observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        adapter.updateWeatherResponse(data)
                        // Hide loading indicator
                    }
                }
                Status.ERROR -> {
                    // Show error message
                    // Hide loading indicator
                }
                Status.LOADING -> {
                    // Show loading indicator
                }
            }
        }
    }
}
