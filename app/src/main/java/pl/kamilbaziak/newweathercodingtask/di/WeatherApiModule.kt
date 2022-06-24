package pl.kamilbaziak.newweathercodingtask.di

import org.koin.dsl.module
import pl.kamilbaziak.newweathercodingtask.utils.provideOpenWeatherApi
import pl.kamilbaziak.newweathercodingtask.utils.provideRetrofit

val weatherApiModule = module {
    factory { provideOpenWeatherApi(get()) }
}