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
class Meal : BaseEntity() {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meal")
    var productsWithWeights: MutableSet<ProductWithWeight> = HashSet()

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate? = null
}