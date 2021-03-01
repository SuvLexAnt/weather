package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.user.MyUserDetails

interface UserRepository {
    fun getUserDetails(username: String): MyUserDetails?
}