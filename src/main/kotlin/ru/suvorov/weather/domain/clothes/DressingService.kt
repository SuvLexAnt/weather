package ru.suvorov.weather.domain.clothes

import ru.suvorov.weather.domain.clothes.SetOfClothes
import ru.suvorov.weather.domain.weather.Weather

interface DressingService {

    fun getRecommendationsByWeather(weather: Weather, temperatureDiff: Int): SetOfClothes
}