package ru.suvorov.weather.api.openweather

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
//@EnableCaching
class Config {

    @Bean
    fun restTemplate() = RestTemplate()
}