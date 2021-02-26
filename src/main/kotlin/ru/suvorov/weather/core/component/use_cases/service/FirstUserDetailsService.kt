package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.port.secondary.UserRepository

@Service
class FirstUserDetailsService(
    @Autowired private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?) =
        userRepository.getUserDetails(username!!)
}