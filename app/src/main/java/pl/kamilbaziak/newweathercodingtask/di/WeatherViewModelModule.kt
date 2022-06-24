package pl.kamilbaziak.newweathercodingtask.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.kamilbaziak.newweathercodingtask.ui.weatherfragment.WeatherViewModel

val weatherViewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}