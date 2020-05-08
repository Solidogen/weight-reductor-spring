package com.spyrdonapps.weightreductor.model.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.HashSet
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
}