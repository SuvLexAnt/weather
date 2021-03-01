package ru.suvorov.weather.core.component.use_cases.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.suvorov.weather.core.port.secondary.UserRepository

@Service
class MyUserDetailsService(
        @Autowired private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails = userRepository.getUserDetails(username!!) ?:
                 throw UsernameNotFoundException("\"$username\" username has not been found")
}