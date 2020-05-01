package com.spyrdonapps.weightreductor.model.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotBlank

@MappedSuperclass
open class NamedEntity : BaseEntity() {

    @Column(name = "name")
//    @NotBlank // TODO re-add this after I deal with null floats
    var name: String? = null

    override fun toString(): String = name ?: ""

}
