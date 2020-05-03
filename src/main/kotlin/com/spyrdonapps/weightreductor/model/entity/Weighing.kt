package com.spyrdonapps.weightreductor.model.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "weighings")
class Weighing : BaseEntity() {

    @Column(name = "weight")
    @NotNull
    var weight: Float? = null

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var date: LocalDate? = null

    override fun toString(): String {
        return "Weighing(weight=$weight, date=$date)"
    }

}
