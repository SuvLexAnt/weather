package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.component.clothes.SetOfClothes
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.component.use_cases.dto.WeatherAndClothesDTO
import ru.suvorov.weather.core.component.weather.Weather
import ru.suvorov.weather.core.port.secondary.WeatherService
import ru.suvorov.weather.core.port.primary.RecommendationService
import ru.suvorov.weather.core.port.secondary.ClothesRepository

@Service
class RecommendationServiceImpl(
        private val weatherService: WeatherService,
        private val clothesRepository: ClothesRepository
) : RecommendationService {

    @Value("\${temperatureDiff}")
    private var temperatureDiff: Int = 0

    override fun getWeatherAndRecommendationsByCity(city: String, temperatureDiffOrNull: Int?): WeatherAndClothesDTO {
        val weather = weatherService.getWeatherByCity(city)
        return WeatherAndClothesDTO(
                weather,
                getClothesByWeather(weather, temperatureDiffOrNull ?: this.temperatureDiff)
        )
    }

    private fun getClothesByWeather(weather: Weather, temperatureDiff: Int): SetOfClothes {
        val hatsAndBody = clothesRepository.getClothesByParams(
                weather.snow,
                weather.rain,
                weather.temperature,
                temperatureDiff).groupBy { it.type }
        return SetOfClothes(
                hatsAndBody[Type.HAT],
                hatsAndBody[Type.BODY])
    }
}