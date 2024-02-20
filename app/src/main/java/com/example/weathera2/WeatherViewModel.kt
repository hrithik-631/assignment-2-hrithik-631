package com.example.weathera2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.liveData
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.liveData
import androidx.lifecycle.liveData
import androidx.lifecycle.liveData
import com.example.weathera2.util.Resource
import com.example.weathera2.util.Status
import android.util.Log

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val city = "Corvallis,OR,US" // Hard-coded city name
    private val apiKey = "06cbdfdbd6b5e7cc3c7a54eb9f601167" // API Key

    val weatherData = liveData {
        emit(Resource.loading(data = null)) // Use Resource class to manage loading state
        try {
            val data = repository.getWeatherForecast(city, apiKey)
            Log.d("WeatherViewModel", "Fetched data: $data")

            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            Log.e("WeatherViewModel", "Error fetching data", exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred!"))
        }
    }
}
