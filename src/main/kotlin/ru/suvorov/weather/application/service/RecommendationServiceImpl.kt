package ru.suvorov.weather.application.service

import org.springframework.stereotype.Service
import ru.suvorov.weather.application.clothes.DressingService
import ru.suvorov.weather.application.domain.weather.WeatherService
import ru.suvorov.weather.application.dto.WeatherAndClothesDTO

@Service
class RecommendationServiceImpl(
        private val weatherService: WeatherService,
        private val dressingService: DressingService
): RecommendationService {

    override fun getWeatherAndRecommendationsByCity(city: String, temperatureDiff: Int): WeatherAndClothesDTO {
        val weather = weatherService.getWeatherByCity(city)
        return WeatherAndClothesDTO(
                weather,
                dressingService.getRecommendationsByWeather(weather, temperatureDiff)
        )
    }
}