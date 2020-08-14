package ru.suvorov.weather.domain.dto

import ru.suvorov.weather.domain.dto.Type.*

class Body(name: String, type: Type = BODY) : Clothes(name, type)