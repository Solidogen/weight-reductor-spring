package com.spyrdonapps.weightreductor.model.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class DeleteWeighingRequest(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val date: LocalDate
)
