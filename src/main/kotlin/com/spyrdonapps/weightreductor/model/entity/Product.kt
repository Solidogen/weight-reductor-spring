package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products")
class Product : NamedEntity() {

    @Column(name = "protein")
    @NotNull
    var protein: Float? = null

    @Column(name = "carbs")
    @NotNull
    var carbs: Float? = null

    @Column(name = "fat")
    @NotNull
    var fat: Float? = null

    @Column(name = "company")
    var company: String? = null

}
