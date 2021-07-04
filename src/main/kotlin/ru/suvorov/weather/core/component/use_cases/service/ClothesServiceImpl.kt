package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.port.primary.ClothesService
import ru.suvorov.weather.core.port.secondary.ClothesRepository

@Service
class ClothesServiceImpl(
        @Autowired private val clothesRepository: ClothesRepository
): ClothesService {
    override fun addClothesById(clothes: Clothes, userId: Long) = clothesRepository.addClothesForUserId(clothes, userId)

    override fun getClothesById(userId: Long?) = clothesRepository.getAllClothesByUserId(userId)
}