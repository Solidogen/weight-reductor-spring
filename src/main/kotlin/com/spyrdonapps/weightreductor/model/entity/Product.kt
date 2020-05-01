package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Digits
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "products")
class Product : NamedEntity() {
    
    @Column(name = "protein")
    @NotEmpty
    @Digits(fraction = 1, integer = 3)
    var protein = 0f

    @Column(name = "carbs")
    @NotEmpty
    @Digits(fraction = 1, integer = 3)
    var carbs = 0f

    @Column(name = "fat")
    @NotEmpty
    @Digits(fraction = 1, integer = 3)
    var fat = 0f
}