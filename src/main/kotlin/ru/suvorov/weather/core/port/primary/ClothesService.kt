package ru.suvorov.weather.core.port.primary

import ru.suvorov.weather.core.component.clothes.Clothes

interface ClothesService {

    fun getClothesByUsername(username: String): List<Clothes>

    fun getClothesIfNotAuthorized(): List<Clothes>

    fun getAllClothes(): List<Clothes>

    fun addClothesByUsername(clothes: Clothes, username: String)
}