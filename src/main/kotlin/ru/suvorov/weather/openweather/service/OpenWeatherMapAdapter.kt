package ru.suvorov.weather.openweather.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.domain.`interface`.WeatherService
import ru.suvorov.weather.domain.dto.Weather
import ru.suvorov.weather.openweather.entity.WeatherDTO
import ru.suvorov.weather.web.OpenWeatherException
import java.lang.RuntimeException
import java.util.*

@Service
class OpenWeatherMapAdapter(
        @Autowired private val restTemplate: RestTemplate,
        @Value("\${apiKey}") private val apiKey: String
) : WeatherService {

    private val units = "metric"

    //TODO: Add Spring Retry functionality to make it fail tolerant
    //TODO: Cover later parsing Unit tests
    //TODO: Add cashing by Redis or Memcached
    override fun getWeatherByCity(city: String): Weather {
        val url = "http://api.openweathermap.org/data/2.5/weather?q=${city}&units=${units}&appid=${apiKey}"
        var response: ResponseEntity<WeatherDTO?>? = null
        try {
            response = restTemplate.getForEntity(url, WeatherDTO::class.java)
            return response.body!!.toWeather()
        } catch (ex: RestClientException) {
            if (response?.statusCode?.isError == true) {
                throw OpenWeatherException("${response.statusCode}")
            } else throw RuntimeException("Unexpected exception")
        }
    }

    private fun WeatherDTO.toWeather()
            = Weather(name, main.temp.toDouble(), wind.speed.toDouble(), main.humidity, rain != null, snow != null)
}