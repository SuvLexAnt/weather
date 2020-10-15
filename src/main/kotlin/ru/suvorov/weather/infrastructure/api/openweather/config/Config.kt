package ru.suvorov.weather.infrastructure.api.openweather.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
//@EnableCaching
class Config {

    @Bean
    fun restTemplate() = RestTemplate()
}