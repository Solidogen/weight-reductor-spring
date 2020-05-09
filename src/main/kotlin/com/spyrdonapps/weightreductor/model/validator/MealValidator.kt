package com.spyrdonapps.weightreductor.model.validator

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight
import com.spyrdonapps.weightreductor.util.extensions.rejectIfLessOrEqualTo
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNull
import com.spyrdonapps.weightreductor.util.extensions.rejectIfNullOrBlank
import com.spyrdonapps.weightreductor.util.utils.required
import org.springframework.validation.Errors

class MealValidator : BaseValidator<Meal>() {

    override val targetType = Meal::class.java

    override fun validate(errorList: Errors, target: Meal) {
        with(target) {
            errorList.rejectIfNullOrBlank(name, "name")
            errorList.rejectIfNull(date, "date")
            errorList.rejectNonPositiveWeights(productsWithWeights)
            errorList.rejectIfAllProductsAreEmpty(productsWithWeights)
            errorList.rejectIfProductNotFullyFilled(productsWithWeights)
        }
    }

    private fun Errors.rejectIfAllProductsAreEmpty(productsWithWeights: List<ProductWithWeight>) {
        var areAllProductsEmpty = false
        productsWithWeights.forEach {
            if (it.product != null || it.weight != null) {
                areAllProductsEmpty = true
            }
        }
        if (!areAllProductsEmpty) {
            rejectValue("productsWithWeights[0].product", required, "Add at least 1 product with weight")
        }
    }

    private fun Errors.rejectIfProductNotFullyFilled(productsWithWeights: List<ProductWithWeight>) {
        productsWithWeights.forEachIndexed { index, it ->
            if (it.product != null && it.weight == null) {
                rejectValue("productsWithWeights[$index].weight", required, "Weight value is required")
            }
            if (it.weight != null && it.product == null) {
                rejectValue("productsWithWeights[$index].product", required, "Product value is required")
            }
        }
    }

    private fun Errors.rejectNonPositiveWeights(productsWithWeights: List<ProductWithWeight>) {
        productsWithWeights.forEachIndexed { index, it ->
            it.weight?.let { weight ->
                rejectIfLessOrEqualTo(weight, "productsWithWeights[$index].weight", 0, "Weight must be higher than 0")
            }
        }
    }
}
