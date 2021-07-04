package ru.suvorov.weather.core.component.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(
        val id: Long,
        private val userName: String,
        private val hashedPassword: String,
        private val enabled: Boolean,
        private val authorities: MutableList<GrantedAuthority> = mutableListOf()
): UserDetails {
    fun setAuthorities(authorities: MutableList<GrantedAuthority>) {
        this.authorities.addAll(authorities)
    }

    override fun getAuthorities() = authorities

    override fun isEnabled() = enabled

    override fun getUsername() = userName

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = hashedPassword

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}