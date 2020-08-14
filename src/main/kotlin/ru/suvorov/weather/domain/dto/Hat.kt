package ru.suvorov.weather.domain.dto

import ru.suvorov.weather.domain.dto.Type.*

class Hat(name: String, type: Type = HAT) : Clothes(name, type)