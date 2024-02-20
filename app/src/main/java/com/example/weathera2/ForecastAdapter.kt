package com.example.weathera2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathera2.databinding.ForecastItemBinding
import android.util.Log

class ForecastAdapter(private var weatherResponse: WeatherResponse?) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val weather = weatherResponse?.weather?.get(position)
        Log.d("ForecastAdapter", "Binding item at position $position: $weather")

        val main = weatherResponse?.main
        holder.binding.weatherDescriptionTextView.text = weather?.description
        if (main != null) {
            holder.binding.temperatureTextView.text = formatTemperature(main.temp)
        }
    }

    override fun getItemCount() = weatherResponse?.weather?.size ?: 0

    class ForecastViewHolder(val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root)

    private fun formatTemperature(temp: Double): String {
        return "${temp}°C" // Formats the temperature to include the degree symbol and Celsius unit
    }

    fun updateWeatherResponse(newWeatherResponse: WeatherResponse?) {
        weatherResponse = newWeatherResponse
        notifyDataSetChanged()
    }
    

}