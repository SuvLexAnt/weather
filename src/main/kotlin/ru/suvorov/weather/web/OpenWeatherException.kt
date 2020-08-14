package ru.suvorov.weather.web

import java.lang.RuntimeException

class OpenWeatherException(message: String?) : RuntimeException(message)