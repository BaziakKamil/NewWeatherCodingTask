package pl.kamilbaziak.newweathercodingtask.repository

import pl.kamilbaziak.newweathercodingtask.api.OpenWeatherAPI
import pl.kamilbaziak.newweathercodingtask.utils.Constants.API_KEY

class OpenWeatherRepository(
    private val openWeatherAPI: OpenWeatherAPI
) {
    suspend fun getCities(
        city: String
    ) = openWeatherAPI.getCities(city, 5, API_KEY)

    suspend fun getTodayWeather(
        lat: Double,
        lon: Double
    ) = openWeatherAPI.getTodayWeather(lat, lon, API_KEY, "metric")

    suspend fun getSevenDayForecast(
        lat: Double,
        lon: Double
    ) = openWeatherAPI.getSevenDaysForecast(lat, lon, "minutely,hourly,alerts,current", API_KEY, "metric")
}