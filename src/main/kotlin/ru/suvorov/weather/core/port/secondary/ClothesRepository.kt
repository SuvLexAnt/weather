package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.clothes.Clothes

interface ClothesRepository {

    fun getClothesByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int): List<Clothes>
}