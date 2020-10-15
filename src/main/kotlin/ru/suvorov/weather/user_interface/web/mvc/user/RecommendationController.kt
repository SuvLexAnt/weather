package ru.suvorov.weather.user_interface.web.mvc.user

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.core.port.primary.RecommendationService

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