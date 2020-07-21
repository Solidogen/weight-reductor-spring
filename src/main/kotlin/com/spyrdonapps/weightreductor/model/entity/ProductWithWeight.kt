package com.spyrdonapps.weightreductor.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.spyrdonapps.weightreductor.util.utils.CalorieUtils
import com.spyrdonapps.weightreductor.util.utils.orZero
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products_with_weights")
class ProductWithWeight : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @ManyToOne
    @JoinColumn(name = "meal_id")
    @JsonIgnore
    var meal: Meal? = null

    @Column(name = "weight")
    @NotNull
    var weight: Float? = null

    val kcal: Float
        get() = CalorieUtils.calculateKcal(this)

    val protein: Float
        get() = weightMultiplier * product?.protein.orZero()

    val carbs: Float
        get() = weightMultiplier * product?.carbs.orZero()

    val fat: Float
        get() = weightMultiplier * product?.fat.orZero()

    val weightMultiplier: Float
        get() = weight.orZero() / 100
}