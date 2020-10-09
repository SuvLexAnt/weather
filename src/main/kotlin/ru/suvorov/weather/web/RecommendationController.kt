package ru.suvorov.weather.web

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.domain.service.RecommendationService
import ru.suvorov.weather.web.exception.OpenWeatherException
import java.lang.RuntimeException
import kotlin.math.log

@Controller
@Slf4j
class RecommendationController(
        @Autowired
        private val recommendationService: RecommendationService
) {

    @GetMapping
    fun index() = "index"

    @GetMapping("/recommendation")
    fun getRecommendationsByCity(
            @RequestParam("city") city: String,
            @RequestParam("temperatureDiff") temperatureDiff: Int,
            modelAndView: ModelAndView): ModelAndView {
        modelAndView.viewName = "index"
        modelAndView.addObject("temperatureDiff", temperatureDiff)
        modelAndView.addObject("weatherAndRecommendations",
                recommendationService.getWeatherAndRecommendationsByCity(city, temperatureDiff))
        return modelAndView
    }
}