package com.spyrdonapps.weightreductor.model.repository

import com.spyrdonapps.weightreductor.model.entity.Weighing
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface WeighingRepository : Repository<Weighing, Int> {

    @Transactional(readOnly = true)
    fun findAll(): List<Weighing>

    fun save(weight: Weighing)

    fun findProductByDate(date: LocalDate): Weighing

}
