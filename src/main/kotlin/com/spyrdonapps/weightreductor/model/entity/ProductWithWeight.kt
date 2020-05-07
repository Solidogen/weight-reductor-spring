package com.spyrdonapps.weightreductor.model.entity

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
    var meal: Meal? = null

    @Column(name = "weight")
    @NotNull
    var weight: Float? = null
}