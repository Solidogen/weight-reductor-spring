package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class NamedEntity : BaseEntity() {

    @Column(name = "name")
    var name: String? = null

    override fun toString(): String = name ?: ""

}
