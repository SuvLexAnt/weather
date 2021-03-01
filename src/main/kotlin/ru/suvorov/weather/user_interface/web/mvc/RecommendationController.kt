package ru.suvorov.weather.user_interface.web.mvc

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.core.component.user.MyUserDetails
import ru.suvorov.weather.core.port.primary.RecommendationService

@Controller
@Slf4j
class RecommendationController(
        @Autowired private val recommendationService: RecommendationService
) {

    @GetMapping
    fun index() = "recommendation/index"

    @GetMapping("/recommendation")
    fun getRecommendationsByCity(
            @RequestParam("city") city: String,
            @RequestParam("temperatureDiff") temperatureDiffOrNull: Int?,
            modelAndView: ModelAndView,
            @AuthenticationPrincipal userDetails: MyUserDetails?): ModelAndView {
        modelAndView.viewName = "recommendation/index"
        modelAndView.addObject("temperatureDiff", temperatureDiffOrNull)
        modelAndView.addObject("weatherAndRecommendations",
                recommendationService.getWeatherAndRecommendationsByCity(city, temperatureDiffOrNull, userDetails?.id))
        return modelAndView
    }
}