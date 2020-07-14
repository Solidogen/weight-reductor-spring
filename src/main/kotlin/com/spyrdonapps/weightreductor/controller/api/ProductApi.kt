package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000", "https://weightreductor.netlify.app/"])
@RequestMapping("/api")
@RestController
class ProductApi(
    private val productRepository: ProductRepository
) {

    @GetMapping("/products")
    fun getAllProducts() = productRepository.findAll().toList()

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable("id") productId: Int) =
        productRepository.findProductById(productId) ?:
        ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID $productId does not exist")
}