package ru.suvorov.weather.domain.service

import org.springframework.stereotype.Service
import ru.suvorov.weather.domain.clothes.DressingService
import ru.suvorov.weather.domain.weather.WeatherService
import ru.suvorov.weather.domain.dto.WeatherAndClothesDTO

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