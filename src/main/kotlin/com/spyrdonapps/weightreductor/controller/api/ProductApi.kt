package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.validator.ProductValidator
import com.spyrdonapps.weightreductor.util.extensions.combinedMultilineError
import com.spyrdonapps.weightreductor.util.utils.localhostUrl
import com.spyrdonapps.weightreductor.util.utils.productionUrl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin(origins = [localhostUrl, productionUrl])
@RequestMapping("/api")
@RestController
class ProductApi(private val productRepository: ProductRepository) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("product")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = ProductValidator()
    }

    @GetMapping("/products")
    fun getAllProducts(): Iterable<Product> = productRepository.findAll()

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable("id") productId: Int) =
        productRepository.findProductById(productId)
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID $productId does not exist")

    @PostMapping("/products/add")
    fun addProduct(@Valid product: Product, result: BindingResult): Any =
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.allErrors.combinedMultilineError)
        } else {
            productRepository.save(product)
        }
}