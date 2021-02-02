package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.clothes.Clothes

interface ClothesRepository {

    /**
     * Returns clothes that matches weather conditions
     * @param snow is there snow outside
     * @param rain is there rain outside
     * @param minTemperature minimal acceptable temperature for clothes
     * @return list of matching clothes
     */
    fun getClothesByParams(snow: Boolean, rain: Boolean, minTemperature: Double): List<Clothes>
}