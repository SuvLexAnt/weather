package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.component.clothes.SetOfClothes
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.component.use_cases.dto.WeatherAndClothesDTO
import ru.suvorov.weather.core.component.weather.Weather
import ru.suvorov.weather.core.port.primary.RecommendationService
import ru.suvorov.weather.core.port.secondary.ClothesRepository
import ru.suvorov.weather.core.port.secondary.WeatherFacade

@Service
class RecommendationServiceImpl(
        private val weatherFacade: WeatherFacade,
        private val clothesRepository: ClothesRepository
) : RecommendationService {

    @Value("\${temperatureDiff}")
    private var temperatureDiff: Int = 0

    override fun getWeatherAndRecommendationsByCity(city: String, temperatureDiffOrNull: Int?, userId: Long?): WeatherAndClothesDTO {
        val weather = weatherFacade.getWeatherByCity(city)
        return WeatherAndClothesDTO(
                weather,
                getClothesByWeather(weather, temperatureDiffOrNull ?: this.temperatureDiff, userId)
        )
    }

    private fun getClothesByWeather(weather: Weather, temperatureDiff: Int, userId: Long?): SetOfClothes {
        val hatsAndBody = clothesRepository.getShortClothesByParamsByUserId(
                weather.snow,
                weather.rain,
                weather.temperature,
                temperatureDiff,
                userId).groupBy { it.type }
        return SetOfClothes(
                hatsAndBody[Type.HAT],
                hatsAndBody[Type.BODY])
    }
}