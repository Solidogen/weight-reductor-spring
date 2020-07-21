package com.spyrdonapps.weightreductor.util.extensions

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight
import com.spyrdonapps.weightreductor.util.utils.orZero

fun Meal.removeEmptyProducts() {
    val listCopy = ArrayList(productsWithWeights)
    listCopy.forEach {
        if (it.product == null && it.weight == null) {
            productsWithWeights.remove(it)
        }
    }
}

fun Meal.mergeSameProductsWeights() {
    val groupedProducts = productsWithWeights.groupBy { it.product?.name.orEmpty() }
    val mergedProducts = mutableListOf<ProductWithWeight>()

    groupedProducts.forEach { (_, productsWithSameName) ->
        mergedProducts.add(ProductWithWeight().apply {
            this.product = productsWithSameName.first().product
            this.weight = productsWithSameName.map { it.weight.orZero() }.sum()
        })
    }
    productsWithWeights = mergedProducts
}