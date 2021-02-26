package ru.suvorov.weather.lib.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfiguration(
        @Autowired private val dataSource: DataSource,
        @Qualifier("firstUserDetailsService") @Autowired
        private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/user**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}