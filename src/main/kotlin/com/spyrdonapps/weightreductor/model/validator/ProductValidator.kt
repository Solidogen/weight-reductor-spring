package com.spyrdonapps.weightreductor.model.validator

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.util.extensions.rejectIfLessThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfMoreThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNull
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNullOrBlank
import org.springframework.validation.Errors
import kotlin.system.measureTimeMillis

class ProductValidator : BaseValidator<Product>() {

    override val targetType = Product::class.java

    override fun validate(errorList: Errors, target: Product) {
        with(target) {
            val time = measureTimeMillis {
                errorList.rejectIfNullOrBlank(::name)
                errorList.rejectIfNull(::protein)
                errorList.rejectIfNull(::carbs)
                errorList.rejectIfNull(::fat)
                errorList.rejectIfLessThan(::protein, 0)
                errorList.rejectIfLessThan(::carbs, 0)
                errorList.rejectIfLessThan(::fat, 0)
                errorList.rejectIfMoreThan(::protein, 100)
                errorList.rejectIfMoreThan(::carbs, 100)
                errorList.rejectIfMoreThan(::fat, 100)
            }
            Unit
        }
    }
}
