package com.spyrdonapps.weightreductor.controller

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("/products")
    fun showAllProducts(model: MutableMap<String, Any>): String {
        val products = productRepository.findAll()
        model["products"] = products
        return "products/allProducts"
    }

    @GetMapping("/products/{id}")
    fun showProduct(@PathVariable("id") productId: Int, model: Model): String {
        val product = productRepository.findProductById(productId)
        model.addAttribute(product)
        return "products/product"
    }

    @PostMapping("/products/add")
    fun addProduct(@Valid product: Product, result: BindingResult): String {
        return if (result.hasErrors()) {
            error("Errors in validation, todo fix this")
        } else {
            productRepository.save(product)
            "redirect:/products/" + product.id
        }
    }
}
