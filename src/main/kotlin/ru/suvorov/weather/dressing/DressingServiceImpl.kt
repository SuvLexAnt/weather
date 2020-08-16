package ru.suvorov.weather.dressing

import org.springframework.stereotype.Service
import ru.suvorov.weather.domain.`interface`.DressingService
import ru.suvorov.weather.domain.dto.Recommendations
import ru.suvorov.weather.domain.dto.Weather

@Service
class DressingServiceImpl(
        private val clothesRepository: ClothesRepository
): DressingService {

    override fun  getRecommendationsByWeather(weather: Weather, temperatureDiff: Int): Recommendations {
        return clothesRepository.getRecommendationsByParams(weather.snow, weather.rain, weather.temperature, temperatureDiff)
    }
}