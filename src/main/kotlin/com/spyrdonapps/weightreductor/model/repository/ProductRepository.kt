package com.spyrdonapps.weightreductor.model.repository

import com.spyrdonapps.weightreductor.model.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional

interface ProductRepository : CrudRepository<Product, Int> {

    fun findProductById(id: Int): Product
}