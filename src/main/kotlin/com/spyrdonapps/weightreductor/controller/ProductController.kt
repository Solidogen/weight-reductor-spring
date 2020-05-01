package com.spyrdonapps.weightreductor.controller

import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.validator.ProductValidator
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
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
        model.addAttribute(product)
        return "products/product"
    }

    @GetMapping("/products/add")
    fun showAddProductForm(model: MutableMap<String, Any>): String {
        model["product"] = Product()
        return "products/addProduct"
    }

    @PostMapping("/products/add")
    fun addProduct(@Valid product: Product, result: BindingResult, model: Model): String {
        return if (result.hasErrors()) {
            model["product"] = Product()
            return "products/addProduct"
        } else {
            productRepository.save(product)
            "redirect:/products/" + product.id
        }
    }

    /*
    * Debugging endpoints for REST-like calls
    * */

    @GetMapping("/products_plain", produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseBody
    fun showAllProductsInPlainText(): String {
        val products = productRepository.findAll()
        return products.joinToString(separator = "\n") { it.toString() }
    }

    @GetMapping("/products_plain/{id}", produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseBody
    fun showProductByIdInPlainText(@PathVariable("id") productId: Int): String {
        val product = productRepository.findProductById(productId)
        return product.toString()
    }
}
