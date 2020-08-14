package ru.suvorov.weather.domain.dto

data class Recommendations(
        val hat: Clothes?,
        val body: List<Clothes>?
)