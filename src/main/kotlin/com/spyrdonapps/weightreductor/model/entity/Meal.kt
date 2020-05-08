package com.spyrdonapps.weightreductor.model.entity

import com.spyrdonapps.weightreductor.util.utils.orZero
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "meals")
class Meal : NamedEntity() {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meal")
    var productsWithWeights: MutableList<ProductWithWeight> = ArrayList()

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate? = null

    val totalCalories: Float
        get() = productsWithWeights.map { it.totalCalories }.sum()

    val totalWeight: Float
        get() = productsWithWeights.map { it.weight.orZero() }.sum()

    val totalProtein: Float
        get() = productsWithWeights.map { it.product?.protein.orZero() }.sum()

    val totalCarbs: Float
        get() = productsWithWeights.map { it.product?.carbs.orZero() }.sum()

    val totalFat: Float
        get() = productsWithWeights.map { it.product?.fat.orZero() }.sum()
}