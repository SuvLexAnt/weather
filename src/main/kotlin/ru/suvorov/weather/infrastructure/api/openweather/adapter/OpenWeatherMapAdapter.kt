package ru.suvorov.weather.infrastructure.api.openweather.adapter

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.core.port.secondary.WeatherFacade
import ru.suvorov.weather.core.component.weather.Weather
import ru.suvorov.weather.infrastructure.api.openweather.dto.WeatherDTO
import lib.logging.logger
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.client.exchange
import ru.suvorov.weather.infrastructure.api.openweather.exception.OpenWeatherException
import java.lang.RuntimeException
import java.util.*

@Slf4j
@Service
class OpenWeatherMapAdapter(
        @Autowired private val restTemplate: RestTemplate,
        @Value("\${apiKey}") private val apiKey: String
) : WeatherFacade {

    private val units = "metric"
    private val log = logger()

    //TODO: Add Spring Retry functionality to make it fail tolerant
    //TODO: Cover later parsing Unit tests
    //@Cacheable("weather", key = "#city")
    override fun getWeatherByCity(city: String): Weather {

        val url = "http://api.openweathermap.org/data/2.5/weather?q=$city&units=$units&appid=$apiKey"

        var response: ResponseEntity<WeatherDTO> = ResponseEntity(HttpStatus.NOT_FOUND)

        try {
            response = restTemplate.getForEntity(url, WeatherDTO::class.java)
            log.info("Response from Weather API received")
            return response.body!!.toWeather()
        } catch (ex: RestClientException) {
            throw handleException(response.statusCode, url, ex)
        }
    }

    private fun WeatherDTO.toWeather()
            = Weather(name, main.temp.toDouble(), wind.speed.toDouble(), main.humidity, rain != null, snow != null)

    @Throws(OpenWeatherException::class)
    private fun handleException(status: HttpStatus, url: String, ex: RestClientException): OpenWeatherException {
        return if (status.isError) {
            log.error("Weather API ${status.ordinal} code received, url = $url")
            OpenWeatherException("$status", ex)
        } else {
            val unexpectedError = "Unexpected error, url = $url"
            log.error(unexpectedError)
            OpenWeatherException(unexpectedError, ex)
        }
    }
}