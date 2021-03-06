package com.spyrdonapps.weightreductor.controller.web

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.validator.ProductValidator
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

@Controller
class ProductController(private val productRepository: ProductRepository) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("product")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = ProductValidator()
    }

    @GetMapping("/products")
    fun showAllProducts(model: MutableMap<String, Any>): String {
        val products = productRepository.findAll()
        model["products"] = products
        return "products/allProducts"
    }

    @GetMapping("/products/{id}")
    fun showProductById(@PathVariable("id") productId: Int, model: Model): String {
        val product = productRepository.findProductById(productId)
        model.addAttribute(product ?: Product())
        return "products/product"
    }

    @GetMapping("/products/add")
    fun initAddProductForm(model: MutableMap<String, Any>): String {
        model["product"] = Product()
        return "products/addProduct"
    }

    @PostMapping("/products/add")
    fun processAddProductForm(@Valid product: Product, result: BindingResult): String {
        return if (result.hasErrors()) {
            return "products/addProduct"
        } else {
            productRepository.save(product)
            "redirect:/products/"
        }
    }
}
