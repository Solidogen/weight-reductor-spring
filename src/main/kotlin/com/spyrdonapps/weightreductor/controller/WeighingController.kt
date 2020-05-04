package com.spyrdonapps.weightreductor.controller

import com.spyrdonapps.weightreductor.model.entity.Weighing
import com.spyrdonapps.weightreductor.model.repository.WeighingRepository
import com.spyrdonapps.weightreductor.model.validator.ProductValidator
import com.spyrdonapps.weightreductor.model.validator.WeightingValidator
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDate
import javax.validation.Valid

@Controller
class WeighingController(private val weighingRepository: WeighingRepository) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("weighing")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = WeightingValidator()
    }

    @GetMapping("/weighings")
    fun showAllWeighings(model: MutableMap<String, Any>): String {
        val weighings = weighingRepository.findAll().sortedBy { it.date }
        model["weighings"] = weighings
        return "weighings/allWeighings"
    }

    @GetMapping("/weighings/add")
    fun initAddWeighingForm(model: MutableMap<String, Any>): String {
        model["weighing"] = Weighing()
        return "weighings/addWeighing"
    }

    @PostMapping("/weighings/add")
    fun processAddWeighingForm(@Valid weighing: Weighing, result: BindingResult, redirectAttributes: RedirectAttributes): String {
        return if (result.hasErrors()) {
            return "weighings/addWeighing"
        } else {
            weighingRepository.save(weighing)
            "redirect:/weighings"
        }
    }
}
