package ru.suvorov.weather.db.dressing

import org.springframework.stereotype.Service
import ru.suvorov.weather.application.clothes.DressingService
import ru.suvorov.weather.application.clothes.SetOfClothes
import ru.suvorov.weather.application.domain.weather.Weather

@Service
class DressingServiceAdapter(
        private val clothesRepository: ClothesRepository
): DressingService {

    override fun  getRecommendationsByWeather(weather: Weather, temperatureDiff: Int): SetOfClothes {
        return clothesRepository.getRecommendationsByParams(weather.snow, weather.rain, weather.temperature, temperatureDiff)
    }
}