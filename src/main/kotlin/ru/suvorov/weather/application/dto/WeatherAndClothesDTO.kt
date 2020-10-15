package ru.suvorov.weather.application.dto

import ru.suvorov.weather.application.clothes.SetOfClothes
import ru.suvorov.weather.application.domain.weather.Weather

data class WeatherAndClothesDTO(
        val weather: Weather,
        val setOfClothes: SetOfClothes
)