package ru.suvorov.weather.core.port.secondary

interface UserRepository {
    fun getUserIdByUsername(username: String): Long
}