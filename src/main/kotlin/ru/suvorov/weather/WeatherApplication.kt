package ru.suvorov.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:application.yaml")
class WeatherApplication

fun main(args: Array<String>) {
    runApplication<WeatherApplication>(*args)
}
