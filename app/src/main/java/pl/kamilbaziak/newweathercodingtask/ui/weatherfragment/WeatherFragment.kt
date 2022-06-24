package pl.kamilbaziak.newweathercodingtask.ui.weatherfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pl.kamilbaziak.newweathercodingtask.R
import pl.kamilbaziak.newweathercodingtask.databinding.FragmentWeatherBinding
import pl.kamilbaziak.newweathercodingtask.utils.Constants
import pl.kamilbaziak.newweathercodingtask.utils.Constants.clockFormat
import pl.kamilbaziak.newweathercodingtask.utils.Constants.dateFormat
import pl.kamilbaziak.newweathercodingtask.utils.Constants.getImageUrl
import java.util.*

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private val viewModel: WeatherViewModel by sharedViewModel()

    private val adapter = SevenDayForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        binding.run {
            super.onViewCreated(view, savedInstanceState)

            recyclerFiveDaysForecast.apply {
                adapter = this@WeatherFragment.adapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }

            viewModel.sevenDayForecast.observe(viewLifecycleOwner) {
                adapter.submitList(it?.daily?.slice(1..5))
            }

            viewModel.todayWeather.observe(viewLifecycleOwner) { todayWeather ->
                if (todayWeather == null) return@observe

                Glide.with(requireContext())
                    .load(getImageUrl(todayWeather.weather[0].icon))
                    .into(imageWeatherState)

                textLocation.text = todayWeather.name

                textUpdated.text = getString(
                    R.string.updated_at,
                    formatUpdatedAtTime(todayWeather.dt)
                )

                textTemp.text = getString(
                    R.string.celcius_degrees,
                    todayWeather.main.temp.toInt().toString()
                )

                textWeatherState.text = todayWeather.weather[0].main

                textMinTemp.text = getString(
                    R.string.min_temp,
                    todayWeather.main.temp_min.toInt().toString()
                )

                textFeelsLike.text = getString(
                    R.string.feels_like,
                    todayWeather.main.feels_like.toInt().toString()
                )

                textMaxTemp.text = getString(
                    R.string.max_temp,
                    todayWeather.main.temp_max.toInt().toString()
                )

                textSunrise.text = getString(
                    R.string.sunrise,
                    formatClockTime(todayWeather.sys.sunrise)
                )

                textSunset.text = getString(
                    R.string.sunset,
                    formatClockTime(todayWeather.sys.sunset)
                )

                textPressure.text = getString(
                    R.string.pressure,
                    todayWeather.main.pressure.toString()
                )

                textWind.text = getString(
                    R.string.wind,
                    todayWeather.wind.speed.toString()
                )

                textHumidity.text = getString(
                    R.string.humidity_with_break,
                    getHumidityPercentage(todayWeather.main.humidity)
                )
            }

            viewModel.getTodayWeather(Constants.WROC_LAT, Constants.WROC_LON)
            viewModel.getSevenDayForecast(Constants.WROC_LAT, Constants.WROC_LON)

            return@run
        }

    private fun formatUpdatedAtTime(value: Long) =
        dateFormat.format(Date(value*1000))

    private fun formatClockTime(value:Long) =
        clockFormat.format(Date(value *1000))

    private fun getHumidityPercentage(value: Int) = "$value%"
}