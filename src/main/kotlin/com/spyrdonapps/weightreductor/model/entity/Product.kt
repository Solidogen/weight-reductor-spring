package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products")
class Product : NamedEntity() {

    @Column(name = "protein")
    @Digits(fraction = 1, integer = 3)
    @NotNull
    var protein: Float? = null

    @Column(name = "carbs")
    @Digits(fraction = 1, integer = 3)
    @NotNull
    var carbs: Float? = null

    @Column(name = "fat")
    @Digits(fraction = 1, integer = 3)
    @NotNull
    var fat: Float? = null

    override fun toString(): String {
        return "Product(name=$name, protein=$protein, carbs=$carbs, fat=$fat)"
    }

}