package pl.kamilbaziak.newweathercodingtask.di

import org.koin.dsl.module
import pl.kamilbaziak.newweathercodingtask.repository.OpenWeatherRepository

val repositoryModule = module {
    factory { OpenWeatherRepository(get()) }
}