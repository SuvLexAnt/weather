package ru.suvorov.weather.web.exception

import java.lang.RuntimeException

class OpenWeatherException(message: String?) : RuntimeException(message)