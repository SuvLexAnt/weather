package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.port.primary.ClothesService
import ru.suvorov.weather.core.port.secondary.ClothesRepository
import ru.suvorov.weather.core.port.secondary.UserRepository

@Service
class ClothesServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val clothesRepository: ClothesRepository
): ClothesService {
    override fun addClothesByUsername(clothes: Clothes, username: String) {
        val userId = userRepository.getUserIdByUsername(username)
        clothesRepository.addClothesForUserId(clothes, userId)
    }

    override fun getClothesByUsername(username: String): List<Clothes> {
        val userId = userRepository.getUserIdByUsername(username)
        return clothesRepository.getAllClothesByUserId(userId)
    }

    override fun getClothesIfNotAuthorized() = clothesRepository.getAllClothesNotAuthorizedUser()

    override fun getAllClothes() = clothesRepository.getAllClothes()
}