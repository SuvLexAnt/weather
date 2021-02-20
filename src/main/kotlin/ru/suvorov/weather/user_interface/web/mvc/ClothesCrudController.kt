package ru.suvorov.weather.user_interface.web.mvc

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.port.primary.ClothesService
import ru.suvorov.weather.core.port.secondary.ClothesRepository
import ru.suvorov.weather.core.port.secondary.UserRepository
import java.security.Principal

@Controller
@Slf4j
@RequestMapping("/clothes")
class ClothesCrudController(
        @Autowired private val clothesService: ClothesService
) {

    @GetMapping
    fun getClothesList(mav: ModelAndView, principal: Principal?): ModelAndView {

        val allUserClothes = if (principal == null) {
            clothesService.getClothesIfNotAuthorized()
        } else {
            clothesService.getClothesByUsername(principal.name)
        }
        mav.viewName = "clothes/clothes"
        mav.addObject("userClothes", allUserClothes)
        mav.addObject("types", Type.values())
        return mav
    }

    @PostMapping
    fun addClothes(clothes: Clothes, mav: ModelAndView, principal: Principal?): ModelAndView {
        if (principal == null) {
            mav.addObject("unauthorizedAttemptToAddClothes", true)
        } else {
            clothesService.addClothesByUsername(clothes, principal.name)
        }
        return getClothesList(mav, principal)
    }
}