package pl.kamilbaziak.newweathercodingtask.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Constants {

    const val PATH_URL = "http://api.openweathermap.org/"

    const val WROC_LAT = 51.10000

    const val WROC_LON = 17.03333

    const val API_KEY = "YOUR_API_CODE_HERE"

    fun getImageUrl(icon: String): Any =
        "http://openweathermap.org/img/wn/$icon@2x.png"

    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

    @SuppressLint("SimpleDateFormat")
    val clockFormat = SimpleDateFormat("HH:mm")
}