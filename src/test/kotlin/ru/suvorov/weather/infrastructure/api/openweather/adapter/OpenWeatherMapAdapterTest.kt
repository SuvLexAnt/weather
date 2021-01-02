package ru.suvorov.weather.infrastructure.api.openweather.adapter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.infrastructure.api.openweather.exception.OpenWeatherException
import java.net.URI
import java.nio.file.Files

@SpringBootTest
class OpenWeatherMapAdapterTest(
        @Autowired private val openWeatherMapAdapter: OpenWeatherMapAdapter,
        @Autowired private val restTemplate: RestTemplate,
        @Autowired private val urlComposer: OpenWeatherUrlComposer
) {

    private var mockServer: MockRestServiceServer = MockRestServiceServer.createServer(restTemplate)
    private val city = "Moscow"


    @Test
    fun correctApiExecution() {
        //Given
        val apiResponse = getFileValue("openweatherApi/weather.json")
        val correctUrl = urlComposer.getWeatherPredictionUrlByCity(city)
        //When
        mockServer.expect(ExpectedCount.once(), requestTo(URI(correctUrl)))
                .andExpect(method(GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(apiResponse))
        //Then
        assert(openWeatherMapAdapter.getWeatherByCity(city).locationName == city)
    }

    @ParameterizedTest
    @ValueSource(strings = ["-wind", "-base", "-name", "-main"])
    fun apiResponseMissingWindParameter(error: String) {
        //Given
        val apiResponse = getFileValue("openweatherApi/weather$error.json")
        val correctUrl = urlComposer.getWeatherPredictionUrlByCity(city)
        //When
        mockServer.expect(ExpectedCount.once(), requestTo(URI(correctUrl)))
                .andExpect(method(GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(apiResponse))
        //Then
        assertThrows<OpenWeatherException> { openWeatherMapAdapter.getWeatherByCity(city) }
    }

    private fun getFileValue(name: String): String {
        val responseFile = ClassPathResource(name).file
        return String(Files.readAllBytes(responseFile.toPath()))
    }
}