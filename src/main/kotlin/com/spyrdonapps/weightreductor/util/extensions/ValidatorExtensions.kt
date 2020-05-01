package com.spyrdonapps.weightreductor.util.extensions

import com.spyrdonapps.weightreductor.util.utils.required
import org.springframework.validation.Errors
import kotlin.reflect.KMutableProperty0

fun Errors.rejectIfNullOrBlank(
    property: KMutableProperty0<String?>,
    message: String = "${property.name.capitalize()} value is required"
) {
    if (property.get().isNullOrBlank()) {
        rejectValue(property.name, required, message)
    }
}

fun Errors.rejectIfNull(
    property: KMutableProperty0<Float?>,
    message: String = "${property.name.capitalize()} value is required"
) {
    if (property.get() == null) {
        rejectValue(property.name, required, message)
    }
}

fun Errors.rejectIfLessThan(
    property: KMutableProperty0<Float?>,
    value: Int,
    message: String = "${property.name.capitalize()} value must be at least $value"
) {
    property.get()?.let {
        if (it < value) {
            rejectValue(property.name, required, message)
        }
    }
}

fun Errors.rejectIfMoreThan(
    property: KMutableProperty0<Float?>,
    value: Int,
    message: String = "${property.name.capitalize()} value must be at most $value"
) {
    property.get()?.let {
        if (it > value) {
            rejectValue(property.name, required, message)
        }
    }
}
