package ru.suvorov.weather.core.component.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(
        private val userName: String,
        private val hashedPassword: String,
        private val enabled: Boolean,
        //TODO: Add multiple authorities
        private val authority: GrantedAuthority
): UserDetails {
    override fun getAuthorities() = mutableListOf(authority)

    override fun isEnabled() = enabled

    override fun getUsername() = userName

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = hashedPassword

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}