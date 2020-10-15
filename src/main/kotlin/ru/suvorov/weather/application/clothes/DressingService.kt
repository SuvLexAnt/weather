package ru.suvorov.weather.application.clothes

import ru.suvorov.weather.application.domain.weather.Weather

interface DressingService {

    fun getRecommendationsByWeather(weather: Weather, temperatureDiff: Int): SetOfClothes
}