package ru.suvorov.weather.api.openweather.service

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.application.domain.weather.WeatherService
import ru.suvorov.weather.application.domain.weather.Weather
import ru.suvorov.weather.api.openweather.entity.WeatherDTO
import ru.suvorov.weather.logging.logger
import ru.suvorov.weather.web.exception.OpenWeatherException
import java.lang.RuntimeException

@Slf4j
@Service
class OpenWeatherMapAdapter(
        @Autowired private val restTemplate: RestTemplate,
        @Value("\${apiKey}") private val apiKey: String
) : WeatherService {

    private val units = "metric"
    private val log = logger()

    //TODO: Add Spring Retry functionality to make it fail tolerant
    //TODO: Cover later parsing Unit tests
//    @Cacheable("weather", key = "#city")
    override fun getWeatherByCity(city: String): Weather {
        val url = "http://api.openweathermap.org/data/2.5/weather?q=${city}&units=${units}&appid=${apiKey}"
        var response: ResponseEntity<WeatherDTO?>? = null
        try {
            response = restTemplate.getForEntity(url, WeatherDTO::class.java)
            log.info("Response from Weather API received")
            return response.body!!.toWeather()
        } catch (ex: RestClientException) {
            if (response?.statusCode?.isError == true) {
                log.error("Weather API exception code received")
                throw OpenWeatherException("${response.statusCode}")
            } else {
                log.error("Unexpected error")
                throw RuntimeException(ex)
            }
        }
    }

    private fun WeatherDTO.toWeather()
            = Weather(name, main.temp.toDouble(), wind.speed.toDouble(), main.humidity, rain != null, snow != null)
}