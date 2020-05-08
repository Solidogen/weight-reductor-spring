package com.spyrdonapps.weightreductor.util.utils

import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight

object CalorieUtils {

    val kcalPerProteinGram = 4
    val kcalPerCarbsGram = 4
    val kcalPerFatGram = 9

    fun calculateTotalCalories(productWithWeight: ProductWithWeight): Float =
        with(productWithWeight) {
            val weightMultiplier = weight.orZero() / 100f
            val kcalFromProtein = product?.protein.orZero() * kcalPerProteinGram
            val kcalFromCarbs = product?.carbs.orZero() * kcalPerCarbsGram
            val kcalFromFat = product?.fat.orZero() * kcalPerFatGram
            weightMultiplier * (kcalFromProtein + kcalFromCarbs + kcalFromFat)
        }
}