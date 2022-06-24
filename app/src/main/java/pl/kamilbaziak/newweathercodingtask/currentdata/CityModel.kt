package pl.kamilbaziak.newweathercodingtask.currentdata

data class CityModel(
    val zip: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String
)
