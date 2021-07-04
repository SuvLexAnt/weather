package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.ClothesShort

interface ClothesRepository {

    fun getShortClothesByParamsByUserId(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int, userId: Long?): List<ClothesShort>

    fun getAllClothesByUserId(userId: Long?): List<Clothes>

    fun addClothesForUserId(clothes: Clothes, userId: Long)
}