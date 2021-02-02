package ru.suvorov.weather.user_interface.web.mvc

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.infrastructure.api.openweather.adapter.OpenWeatherUrlComposer
import ru.suvorov.weather.user_interface.web.mvc.user.RecommendationController

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MVCExceptionHandlerTest(
        @LocalServerPort private val port: Int,
        @Autowired private val testRestTemplate: TestRestTemplate,
        @Autowired private val urlComposer: OpenWeatherUrlComposer
) {
    private val city = "Moscow"
    private val internRecommendationUrl = "http://localhost:$port/recommendation?temperatureDiff=9&city=$city"
    @MockkBean
    private lateinit var mockRecommendationController: RecommendationController

    @Test
    fun allExceptionHandler() {
        //Given
        val view = ModelAndView()
        //When
        every {mockRecommendationController.getRecommendationsByCity(city, null, view)} throws Exception()
        //Then
        val htmlPage = testRestTemplate.getForObject(internRecommendationUrl, String::class.java)
        assert(htmlPage.contains("№500 error happened")
          .and(htmlPage.contains("The good news are we have a funny picture down here just in case"))
          .and(htmlPage.contains("It's a funny picture, all right?")))
    }
}