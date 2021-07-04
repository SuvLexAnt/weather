package ru.suvorov.weather.core.port.primary

import ru.suvorov.weather.core.component.clothes.Clothes

interface ClothesService {

    fun getClothesById(userId: Long?): List<Clothes>

    fun addClothesById(clothes: Clothes, userId: Long)
}