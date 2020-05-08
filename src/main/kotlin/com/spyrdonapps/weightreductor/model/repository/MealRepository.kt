package com.spyrdonapps.weightreductor.model.repository

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.Product
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface MealRepository : CrudRepository<Meal, Int> {

    fun findMealById(id: Int): Meal

    fun findMealsByDate(date: LocalDate): List<Meal>
}