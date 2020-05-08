package com.spyrdonapps.weightreductor.controller

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight
import com.spyrdonapps.weightreductor.model.repository.MealRepository
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
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
            var canSave = true
            meal.productsWithWeights.forEach {
                // not whole item is filled
                if ((it.product != null && it.weight == null) || (it.product == null && it.weight != null)) {
                    canSave = false
                }
            }
            if (canSave) {
                // delete fully unused
                meal.productsWithWeights.reversed().forEach {
                    if (it.product == null && it.weight == null) {
                        meal.productsWithWeights.remove(it)
                    }
                }
                meal.productsWithWeights.forEach { it.meal = meal }
                mealRepository.save(meal)
                "redirect:/meals/"
            } else {
                return "meals/addMeal"
            }
        }
    }
}
