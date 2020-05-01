package com.spyrdonapps.weightreductor.util.extensions

import com.spyrdonapps.weightreductor.util.utils.required
import org.springframework.validation.Errors

fun Errors.rejectIfNullOrBlank(
    value: String?,
    propertyName: String,
    message: String = "${propertyName.capitalize()} value is required"
) {
    if (value.isNullOrBlank()) {
        rejectValue(propertyName, required, message)
    }
}

fun Errors.rejectIfNull(
    value: Any?,
    propertyName: String,
    message: String = "${propertyName.capitalize()} value is required"
) {
    if (fieldErrors.find { it.field == propertyName }?.isBindingFailure == true) {
        // skip if already a type mismatch
        return
    }
    if (value == null) {
        rejectValue(propertyName, required, message)
    }
}

fun Errors.rejectIfLessThan(
    value: Float?,
    propertyName: String,
    minimum: Int,
    message: String = "${propertyName.capitalize()} value must be at least $minimum"
) {
    value?.let {
        if (it < minimum) {
            rejectValue(propertyName, required, message)
        }
    }
}

fun Errors.rejectIfMoreThan(
    value: Float?,
    propertyName: String,
    maximum: Int,
    message: String = "${propertyName.capitalize()} value must be at most $maximum"
) {
    value?.let {
        if (it > maximum) {
            rejectValue(propertyName, required, message)
        }
    }
}
