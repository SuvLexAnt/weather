package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.ClothesShort

interface ClothesRepository {

    fun getClothesByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int): List<ClothesShort>

    //TODO: Add pagination
    fun getAllClothes(): List<Clothes>

    fun getAllClothesByUserId(userId: Long): List<Clothes>

    fun getAllClothesNotAuthorizedUser(): List<Clothes>

    fun addClothesForUserId(clothes: Clothes, userId: Long)
}