package com.spyrdonapps.weightreductor.model.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class AddWeighingRequest(
    val weight: Float,
    // todo check if needed with @Valid. also can try parse in validator
    // less magic -> better, most of the time
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val date: LocalDate
)