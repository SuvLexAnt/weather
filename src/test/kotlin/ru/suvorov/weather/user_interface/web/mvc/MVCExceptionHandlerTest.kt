package ru.suvorov.weather.user_interface.web.mvc

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.web.client.RestTemplate
import ru.suvorov.weather.infrastructure.api.openweather.adapter.OpenWeatherUrlComposer
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MVCExceptionHandlerTest(
        @LocalServerPort private val port: Int,
        @Autowired private val testRestTemplate: TestRestTemplate,
        @Autowired private val restTemplate: RestTemplate,
        @Autowired private val urlComposer: OpenWeatherUrlComposer
) {

    private var mockServer: MockRestServiceServer = MockRestServiceServer.createServer(restTemplate)
    private val city = "Moscow"
    private val recommendationFailUrl = "http://localhost:$port/recommendation?temperatureDiff=9&city=$city"

    @Test
    fun testTest() {
        //Given
        val correctUrl = urlComposer.getWeatherPredictionUrlByCity(city)
        //When
        mockServer.expect(ExpectedCount.once(), requestTo(URI(correctUrl)))
                .andExpect(method(GET))
                .andRespond(withStatus(NOT_FOUND))
        //Then
        assert(testRestTemplate.getForObject(recommendationFailUrl, String::class.java).contains("№500 error happened"))
    }
}