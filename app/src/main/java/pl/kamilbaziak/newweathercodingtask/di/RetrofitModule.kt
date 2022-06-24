package pl.kamilbaziak.newweathercodingtask.di

import org.koin.dsl.module
import pl.kamilbaziak.newweathercodingtask.utils.provideRetrofit

val retrofitModule = module {
    single { provideRetrofit() }
}