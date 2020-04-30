package com.spyrdonapps.weightreductor.model.repository

import com.spyrdonapps.weightreductor.model.entity.Product
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional

interface ProductRepository : Repository<Product, Int> {

    @Transactional(readOnly = true)
    fun findAll(): List<Product>

    fun save(product: Product)

    fun findProductById(id: Int): Product
}