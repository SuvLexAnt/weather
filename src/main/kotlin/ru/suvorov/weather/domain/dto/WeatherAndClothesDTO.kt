package ru.suvorov.weather.domain.dto

import ru.suvorov.weather.domain.clothes.SetOfClothes
import ru.suvorov.weather.domain.weather.Weather

data class WeatherAndClothesDTO(
        val weather: Weather,
        val setOfClothes: SetOfClothes
)