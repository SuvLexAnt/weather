package ru.suvorov.weather.core.component.clothes

open class Clothes(
        val id: Long?,
        override val name: String,
        override val type: Type,
        val temperature: Int,
        val snow: Boolean,
        val rain: Boolean
): ClothesShort(name, type)