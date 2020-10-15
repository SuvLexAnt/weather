package ru.suvorov.weather.infrastructure.api.openweather.exception

import java.lang.RuntimeException

class OpenWeatherException(message: String?) : RuntimeException(message)