package com.spyrdonapps.weightreductor.model.validator

import com.spyrdonapps.weightreductor.model.entity.Weighing
import com.spyrdonapps.weightreductor.util.extensions.rejectIfLessThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfMoreThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNull
import org.springframework.validation.Errors

class WeightingValidator : BaseValidator<Weighing>() {

    override val targetType = Weighing::class.java

    override fun validate(errorList: Errors, target: Weighing) {
        with(target) {
            errorList.rejectIfNull(weight, "weight")
            errorList.rejectIfLessThan(weight, "weight", 0)
            errorList.rejectIfMoreThan(weight, "weight", 999)
            errorList.rejectIfNull(date, "date")
        }
    }
}
