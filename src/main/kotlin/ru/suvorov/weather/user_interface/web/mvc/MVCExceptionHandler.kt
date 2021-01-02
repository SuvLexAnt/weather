package ru.suvorov.weather.user_interface.web.mvc

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.suvorov.weather.infrastructure.api.openweather.exception.OpenWeatherException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class MVCExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(OpenWeatherException::class)
    fun handleApiException(req: HttpServletRequest): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "error/errorPage"

        modelAndView.addObject("ErrorNum",500)
        return modelAndView
    }
}