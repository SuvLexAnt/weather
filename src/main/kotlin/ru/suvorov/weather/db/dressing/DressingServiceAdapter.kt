package ru.suvorov.weather.db.dressing

import org.springframework.stereotype.Service
import ru.suvorov.weather.domain.clothes.DressingService
import ru.suvorov.weather.domain.clothes.SetOfClothes
import ru.suvorov.weather.domain.weather.Weather

@Service
class DressingServiceAdapter(
        private val clothesRepository: ClothesRepository
): DressingService {

    override fun  getRecommendationsByWeather(weather: Weather, temperatureDiff: Int): SetOfClothes {
        return clothesRepository.getRecommendationsByParams(weather.snow, weather.rain, weather.temperature, temperatureDiff)
    }
}