package com.spyrdonapps.weightreductor.model.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class AddWeighingRequest(
    val weight: Float,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val date: LocalDate
)
