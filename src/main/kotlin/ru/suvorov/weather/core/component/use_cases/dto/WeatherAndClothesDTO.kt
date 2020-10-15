package ru.suvorov.weather.core.component.use_cases.dto

import ru.suvorov.weather.core.component.clothes.SetOfClothes
import ru.suvorov.weather.core.component.weather.Weather

data class WeatherAndClothesDTO(
        val weather: Weather,
        val setOfClothes: SetOfClothes
)