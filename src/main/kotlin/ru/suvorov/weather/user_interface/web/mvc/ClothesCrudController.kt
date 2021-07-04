package ru.suvorov.weather.user_interface.web.mvc

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.component.user.MyUserDetails
import ru.suvorov.weather.core.port.primary.ClothesService

@Controller
@Slf4j
@RequestMapping("/clothes")
class ClothesCrudController(
        @Autowired private val clothesService: ClothesService
) {

    @GetMapping
    fun getClothesList(mav: ModelAndView, @AuthenticationPrincipal userDetails: MyUserDetails?): ModelAndView {
        val allUserClothes = clothesService.getClothesById(userDetails?.id)
        mav.viewName = "clothes/clothes"
        mav.addObject("userClothes", allUserClothes)
        mav.addObject("types", Type.values())
        return mav
    }

    @PostMapping
    fun addClothes(clothes: Clothes, mav: ModelAndView, @AuthenticationPrincipal userDetails: MyUserDetails?): ModelAndView {
        if (userDetails == null) {
            mav.addObject("unauthorizedAttemptToAddClothes", true)
        } else {
            clothesService.addClothesById(clothes, userDetails.id)
        }
        return getClothesList(mav, userDetails)
    }
}