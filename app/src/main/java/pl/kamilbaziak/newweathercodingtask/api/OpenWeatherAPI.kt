package pl.kamilbaziak.newweathercodingtask.api

import pl.kamilbaziak.newweathercodingtask.currentdata.CityModel
import pl.kamilbaziak.newweathercodingtask.currentdata.TodayWeatherModel
import pl.kamilbaziak.newweathercodingtask.sevendaysdata.SevenDayForecastModel
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("/geo/1.0/direct?")
    suspend fun getCities(
        @Query("q") city: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): List<CityModel>?

    @GET("/data/2.5/weather")
    suspend fun getTodayWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") system: String
    ): TodayWeatherModel?

    @GET("/data/2.5/onecall")
    suspend fun getSevenDaysForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") system: String
    ): SevenDayForecastModel?
}