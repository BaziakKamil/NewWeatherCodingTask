package pl.kamilbaziak.newweathercodingtask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.kamilbaziak.newweathercodingtask.di.*

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    retrofitModule,
                    weatherApiModule,
                    repositoryModule,
                    weatherViewModelModule
                )
            )
        }
    }
}