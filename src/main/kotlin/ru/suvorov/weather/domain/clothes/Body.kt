package ru.suvorov.weather.domain.clothes

import ru.suvorov.weather.domain.clothes.Type.*

class Body(name: String, type: Type = BODY) : Clothes(name, type)