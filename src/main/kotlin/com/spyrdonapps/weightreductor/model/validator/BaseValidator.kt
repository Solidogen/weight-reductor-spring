package com.spyrdonapps.weightreductor.model.validator

import org.springframework.validation.Errors
import org.springframework.validation.Validator

abstract class BaseValidator<T> : Validator {

    abstract val targetType: Class<T>

    @Suppress("UNCHECKED_CAST")
    final override fun validate(target: Any, errors: Errors) {
        validate(errors, target as T)
    }

    final override fun supports(clazz: Class<*>): Boolean = targetType.isAssignableFrom(clazz)

    abstract fun validate(errorList: Errors, target: T)
}
