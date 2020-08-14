package ru.suvorov.weather.domain.service

import org.springframework.stereotype.Service
import ru.suvorov.weather.domain.`interface`.DressingService
import ru.suvorov.weather.domain.`interface`.WeatherService
import ru.suvorov.weather.domain.dto.WeatherAndRecommendations

@Service
class RecommendationServiceImpl(
        private val weatherService: WeatherService,
        private val dressingService: DressingService
): RecommendationService {

    override fun getWeatherAndRecommendationsByCity(city: String): WeatherAndRecommendations {
        val weather = weatherService.getWeatherByCity(city)
        return WeatherAndRecommendations(
                weather,
                dressingService.getRecommendationsByWeather(weather)
        )
    }
}