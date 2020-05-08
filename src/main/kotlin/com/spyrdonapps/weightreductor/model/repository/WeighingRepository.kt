package com.spyrdonapps.weightreductor.model.repository

import com.spyrdonapps.weightreductor.model.entity.Weighing
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface WeighingRepository : CrudRepository<Weighing, Int> {

    @Query("SELECT w FROM Weighing w WHERE w.date = :date")
    fun findWeighingByDate(@Param("date") date: LocalDate): Weighing?

    @Query("UPDATE Weighing w SET w.weight = :weight WHERE w.id = :id")
    @Modifying
    @Transactional
    fun updateWeightById(@Param("id") id: Int, @Param("weight") weight: Float)
}
