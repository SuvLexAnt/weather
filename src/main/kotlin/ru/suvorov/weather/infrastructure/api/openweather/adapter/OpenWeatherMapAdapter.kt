package ru.suvorov.weather.infrastructure.api.openweather.adapter

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.core.port.secondary.WeatherFacade
import ru.suvorov.weather.core.component.weather.Weather
import ru.suvorov.weather.infrastructure.api.openweather.dto.WeatherDTO
import lib.logging.logger
import org.springframework.http.HttpStatus
import ru.suvorov.weather.infrastructure.api.openweather.exception.OpenWeatherException

@Slf4j
@Service
class OpenWeatherMapAdapter(
        @Autowired private val restTemplate: RestTemplate,
        @Autowired private val urlComposer: OpenWeatherUrlComposer
) : WeatherFacade {

    private val log = logger()

    //TODO: Add Spring Retry functionality to make it fail tolerant
    //TODO: Cover later parsing Unit tests
    //@Cacheable("weather", key = "#city")
    override fun getWeatherByCity(city: String): Weather {

        val url = urlComposer.getWeatherPredictionUrlByCity(city)

        var response: ResponseEntity<WeatherDTO> = ResponseEntity(HttpStatus.NOT_FOUND)

        try {
            response = restTemplate.getForEntity(url, WeatherDTO::class.java)
            log.info("Response from Weather API received")
            return response.body!!.toWeather()
        } catch (ex: RestClientException) {
            val urlForLogs = urlComposer.getWeatherPredictionUrlByCity(city, true)
            throw handleException(response.statusCode, urlForLogs, ex)
        }
    }

    private fun WeatherDTO.toWeather()
            = Weather(name, main.temp.toDouble(), wind.speed.toDouble(), main.humidity, rain != null, snow != null)

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