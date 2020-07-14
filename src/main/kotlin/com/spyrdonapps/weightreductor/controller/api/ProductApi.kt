package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.response.ProductsResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000", "https://weightreductor.netlify.app/"])
@RequestMapping("/api")
@RestController
class ProductApi(
    private val productRepository: ProductRepository
) {

    @GetMapping("/products")
    fun getAllProducts() = ProductsResponse(products = productRepository.findAll().toList())
}