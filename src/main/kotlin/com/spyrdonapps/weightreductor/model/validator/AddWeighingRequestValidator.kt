package com.spyrdonapps.weightreductor.model.validator

import com.spyrdonapps.weightreductor.model.request.AddWeighingRequest
import com.spyrdonapps.weightreductor.util.extensions.rejectIfLessThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfMoreThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNull
import org.springframework.validation.Errors

class AddWeighingRequestValidator : BaseValidator<AddWeighingRequest>() {

    override val targetType = AddWeighingRequest::class.java

    override fun validate(errorList: Errors, target: AddWeighingRequest) {
        with(target) {
            errorList.rejectIfNull(weight, "weight")
            errorList.rejectIfLessThan(weight, "weight", 0)
            errorList.rejectIfMoreThan(weight, "weight", 999)
            errorList.rejectIfNull(date, "date")
        }
    }
}
