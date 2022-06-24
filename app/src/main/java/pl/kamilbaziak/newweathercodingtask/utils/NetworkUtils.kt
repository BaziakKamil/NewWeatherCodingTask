package pl.kamilbaziak.newweathercodingtask.utils

import pl.kamilbaziak.newweathercodingtask.api.OpenWeatherAPI
import pl.kamilbaziak.newweathercodingtask.utils.Constants.PATH_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(PATH_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOpenWeatherApi(retrofit: Retrofit): OpenWeatherAPI =
    retrofit.create(OpenWeatherAPI::class.java)