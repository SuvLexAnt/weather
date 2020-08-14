package ru.suvorov.weather.domain.`interface`

import ru.suvorov.weather.domain.dto.Recommendations
import ru.suvorov.weather.domain.dto.Weather

interface DressingService {

    fun getRecommendationsByWeather(weather: Weather): Recommendations
}