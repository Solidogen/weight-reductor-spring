package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.entity.Weighing
import com.spyrdonapps.weightreductor.model.repository.WeighingRepository
import com.spyrdonapps.weightreductor.model.request.AddWeighingRequest
import com.spyrdonapps.weightreductor.model.request.DeleteWeighingRequest
import com.spyrdonapps.weightreductor.model.validator.AddWeighingRequestValidator
import com.spyrdonapps.weightreductor.util.extensions.combinedMultilineError
import com.spyrdonapps.weightreductor.util.utils.localhostUrl
import com.spyrdonapps.weightreductor.util.utils.productionUrl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin(origins = [localhostUrl, productionUrl])
@RequestMapping("/api")
@RestController
class WeighingApi(private val weighingRepository: WeighingRepository) {

    @InitBinder("addWeighingRequest")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = AddWeighingRequestValidator()
    }

    @GetMapping("/weighings")
    fun getAllWeighings() = weighingRepository.findAll().sortedBy { it.date }

    @PostMapping("/weighings/add")
    fun addWeighing(@RequestBody @Valid addWeighingRequest: AddWeighingRequest, result: BindingResult): Any? =
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.allErrors.combinedMultilineError)
        } else {
            val cachedWeighing = weighingRepository.findWeighingByDate(addWeighingRequest.date)
            if (cachedWeighing != null) {
                cachedWeighing.weight = addWeighingRequest.weight
                weighingRepository.save(cachedWeighing)
            } else {
                val newWeighing = Weighing().apply {
                    date = addWeighingRequest.date
                    weight = addWeighingRequest.weight
                }
                weighingRepository.save(newWeighing)
            }
        }

    @PostMapping("/weighings/delete")
    fun deleteWeighing(@RequestBody deleteWeighingRequest: DeleteWeighingRequest, result: BindingResult): Any? =
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.allErrors.combinedMultilineError)
        } else {
            weighingRepository.deleteWeighingByDate(date = deleteWeighingRequest.date)
        }
}