package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products")
class Product : NamedEntity() {

    @Column(name = "protein", precision = 5, scale = 1)
    @NotNull
    var protein: Float? = null

    @Column(name = "carbs", precision = 5, scale = 1)
    @NotNull
    var carbs: Float? = null

    @Column(name = "fat", precision = 5, scale = 1)
    @NotNull
    var fat: Float? = null

    override fun toString(): String {
        return "Product(name=$name, protein=$protein, carbs=$carbs, fat=$fat)"
    }

}