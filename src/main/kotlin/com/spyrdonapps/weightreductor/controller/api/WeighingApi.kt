package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.entity.Weighing
import com.spyrdonapps.weightreductor.model.repository.WeighingRepository
import com.spyrdonapps.weightreductor.model.validator.WeightingValidator
import com.spyrdonapps.weightreductor.util.extensions.combinedMultilineError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin(origins = ["http://localhost:3000", "https://weightreductor.netlify.app/"])
@RequestMapping("/api")
@RestController
class WeighingApi(private val weighingRepository: WeighingRepository) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("weighing")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = WeightingValidator()
    }

    @GetMapping("/weighings")
    fun getAllWeighings() = weighingRepository.findAll().sortedBy { it.date }

    @PostMapping("/weighings/add")
    fun addWeighing(@Valid weighing: Weighing, result: BindingResult): Any? =
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.allErrors.combinedMultilineError)
        } else {
            val cachedWeighing = weighingRepository.findWeighingByDate(weighing.date!!)
            if (cachedWeighing != null) {
                cachedWeighing.weight = weighing.weight
                weighingRepository.save(cachedWeighing)
            } else {
                weighingRepository.save(weighing)
            }
        }
}