package pl.kamilbaziak.newweathercodingtask.ui.weatherfragment

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import pl.kamilbaziak.newweathercodingtask.repository.OpenWeatherRepository
import pl.kamilbaziak.newweathercodingtask.currentdata.TodayWeatherModel
import pl.kamilbaziak.newweathercodingtask.sevendaysdata.SevenDayForecastModel

class WeatherViewModel(
    private val openWeatherRepository: OpenWeatherRepository
) : ViewModel(), KoinComponent {

    private val _todayWeather: MutableLiveData<TodayWeatherModel?> = MutableLiveData()
    val todayWeather:LiveData<TodayWeatherModel?> = _todayWeather

    private val _sevenDayForecast: MutableLiveData<SevenDayForecastModel?> = MutableLiveData()
    val sevenDayForecast:LiveData<SevenDayForecastModel?> = _sevenDayForecast

    fun getCities(city: String?) =
        viewModelScope.launch {
            val result = city?.let { openWeatherRepository.getCities(it) }
            result?.get(0)?.let {
                getTodayWeather(it.lat, result[0].lon)
                getSevenDayForecast(it.lat, result[0].lon)
            }
        }

    fun getSevenDayForecast(lat: Double, lon: Double) =
        viewModelScope.launch {
            val result = openWeatherRepository.getSevenDayForecast(lat, lon)
            _sevenDayForecast.value = result
        }

    fun getTodayWeather(lat: Double, lon: Double) =
        viewModelScope.launch {
            val result = openWeatherRepository.getTodayWeather(lat, lon)
            _todayWeather.value = result
        }
}