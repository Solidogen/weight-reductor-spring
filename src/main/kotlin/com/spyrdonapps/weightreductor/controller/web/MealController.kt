package com.spyrdonapps.weightreductor.controller.web

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight
import com.spyrdonapps.weightreductor.model.repository.MealRepository
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.validator.MealValidator
import com.spyrdonapps.weightreductor.util.utils.orZero
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class MealController(
    private val mealRepository: MealRepository,
    private val productRepository: ProductRepository
) {

    @ModelAttribute("products")
    fun allProducts(): Iterable<Product> = productRepository.findAll().sortedBy { it.name } // todo sort in hibernate

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("meal")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = MealValidator()
    }

    @GetMapping("/meals")
    fun showAllMeals(model: MutableMap<String, Any>): String {
        val meals = mealRepository.findAll()
        model["meals"] = meals
        return "meals/allMeals"
    }

    @GetMapping("/meals/add")
    fun initAddMealForm(model: MutableMap<String, Any>): String {
        model["meal"] = Meal().apply {
            repeat(6) {
                productsWithWeights.add(ProductWithWeight())
            }
        }
        return "meals/addMeal"
    }

    @PostMapping("/meals/add")
    fun processAddMealForm(@Valid meal: Meal, result: BindingResult): String {
        return if (result.hasErrors()) {
            return "meals/addMeal"
        } else {
            meal.removeEmptyProducts()
            meal.mergeSameProductsWeights()
            meal.productsWithWeights.forEach { it.meal = meal }
            mealRepository.save(meal)
            "redirect:/meals/"
        }
    }

    private fun Meal.removeEmptyProducts() {
        val listCopy = ArrayList(productsWithWeights)
        listCopy.forEach {
            if (it.product == null && it.weight == null) {
                productsWithWeights.remove(it)
            }
        }
    }

    private fun Meal.mergeSameProductsWeights() {
        val groupedProducts = productsWithWeights.groupBy { it.product?.name.orEmpty() }
        val mergedProducts = mutableListOf<ProductWithWeight>()

        groupedProducts.forEach { (_, productsWithSameName) ->
            mergedProducts.add(ProductWithWeight().apply {
                this.product = productsWithSameName.first().product
                this.weight = productsWithSameName.map { it.weight.orZero() }.sum()
            })
        }
        productsWithWeights = mergedProducts
    }
}
