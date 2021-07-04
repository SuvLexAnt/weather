package ru.suvorov.weather.user_interface.web.mvc

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import ru.suvorov.weather.core.component.user.MyUserDetails

@ControllerAdvice
class LoginControllerInterceptor {

    @ModelAttribute("login")
    fun getLogin(@AuthenticationPrincipal userDetails: MyUserDetails?) = userDetails?.username
}