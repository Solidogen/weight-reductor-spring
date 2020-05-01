package com.spyrdonapps.weightreductor.model.validator

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.util.extensions.rejectIfLessThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfMoreThan
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNull
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNullOrBlank
import org.springframework.validation.Errors

class ProductValidator : BaseValidator<Product>() {

    override val targetType = Product::class.java

    override fun validate(errorList: Errors, target: Product) {
        with(target) {
            errorList.rejectIfNullOrBlank(name, "name")
            errorList.rejectIfNull(protein, "protein")
            errorList.rejectIfNull(carbs, "carbs")
            errorList.rejectIfNull(fat, "fat")
            errorList.rejectIfLessThan(protein, "protein", 0)
            errorList.rejectIfLessThan(carbs, "carbs", 0)
            errorList.rejectIfLessThan(fat, "fat", 0)
            errorList.rejectIfMoreThan(protein, "protein", 100)
            errorList.rejectIfMoreThan(carbs, "carbs", 100)
            errorList.rejectIfMoreThan(fat, "fat", 100)
        }
    }
}
