package ru.suvorov.weather.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.domain.service.RecommendationService
import java.lang.RuntimeException

@Controller
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
        try {
            modelAndView.addObject("weatherAndRecommendations",
                    recommendationService.getWeatherAndRecommendationsByCity(city, temperatureDiff))
        } catch (ex: RuntimeException) {
            //TODO: Handle this exception by AOP in different class and in different way for unknown exception and
            // OpenWeatherException
            print("Exception")
        }
        return modelAndView
    }
}