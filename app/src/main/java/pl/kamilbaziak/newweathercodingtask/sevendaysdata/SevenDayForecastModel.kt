package pl.kamilbaziak.newweathercodingtask.sevendaysdata

data class SevenDayForecastModel(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)