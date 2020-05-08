package com.spyrdonapps.weightreductor.util.utils

import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight

object CalorieUtils {

    private const val kcalPerProteinGram = 4
    private const val kcalPerCarbsGram = 4
    private const val kcalPerFatGram = 9

    fun calculateKcal(productWithWeight: ProductWithWeight): Float =
        with(productWithWeight) {
            val kcalFromProtein = product?.protein.orZero() * kcalPerProteinGram
            val kcalFromCarbs = product?.carbs.orZero() * kcalPerCarbsGram
            val kcalFromFat = product?.fat.orZero() * kcalPerFatGram
            weightMultiplier * (kcalFromProtein + kcalFromCarbs + kcalFromFat)
        }
}