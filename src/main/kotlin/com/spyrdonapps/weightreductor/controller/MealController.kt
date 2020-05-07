package com.spyrdonapps.weightreductor.controller

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.entity.Product
import com.spyrdonapps.weightreductor.model.entity.ProductWithWeight
import com.spyrdonapps.weightreductor.model.repository.MealRepository
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class MealController(private val mealRepository: MealRepository) {

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
        model["meal"] = Meal()
        return "meals/addMeal"
    }

    @PostMapping("/meals/add")
    fun processAddMealForm(@Valid meal: Meal, result: BindingResult): String {
        return if (result.hasErrors()) {
            return "meals/addMeal"
        } else {

            val productWithWeight1 = ProductWithWeight().apply {
                weight = kotlin.random.Random.nextInt(1, 100).toFloat()
                product = Product().apply { id = 1 }
                this.meal = meal
            }
            val productWithWeight2 = ProductWithWeight().apply {
                weight = kotlin.random.Random.nextInt(1, 100).toFloat()
                product = Product().apply { id = 2 }
                this.meal = meal
            }
            val productWithWeight3 = ProductWithWeight().apply {
                weight = kotlin.random.Random.nextInt(1, 100).toFloat()
                product = Product().apply { id = 3 }
                this.meal = meal
            }
                meal.productsWithWeights.add(productWithWeight1)
                meal.productsWithWeights.add(productWithWeight2)
                meal.productsWithWeights.add(productWithWeight3)

            mealRepository.save(meal)
            "redirect:/meals/"
        }
    }
}
