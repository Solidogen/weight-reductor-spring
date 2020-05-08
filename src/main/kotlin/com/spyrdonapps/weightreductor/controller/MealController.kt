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
    fun allProducts(): Collection<Product> = productRepository.findAll()

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
            repeat(2) {
                productsWithWeights.add(ProductWithWeight().apply {
//                    weight = 10f
                })
            }
        }
        return "meals/addMeal"
    }

    @PostMapping("/meals/add")
    fun processAddMealForm(@Valid meal: Meal, result: BindingResult): String {
        return if (result.hasErrors()) {
            return "meals/addMeal"
        } else {
            meal.productsWithWeights.forEach { it.meal = meal }
            mealRepository.save(meal)
            "redirect:/meals/"
        }
    }
}
