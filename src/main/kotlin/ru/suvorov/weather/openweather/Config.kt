package ru.suvorov.weather.openweather

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.web.client.RestTemplate

@Configuration
@PropertySource("classpath:application.yml")
class Config {

    @Bean
    fun restTemplate() = RestTemplate()
}