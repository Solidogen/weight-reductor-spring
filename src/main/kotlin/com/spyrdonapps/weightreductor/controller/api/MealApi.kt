package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.entity.Meal
import com.spyrdonapps.weightreductor.model.repository.MealRepository
import com.spyrdonapps.weightreductor.model.repository.ProductRepository
import com.spyrdonapps.weightreductor.model.validator.MealValidator
import com.spyrdonapps.weightreductor.util.extensions.combinedMultilineError
import com.spyrdonapps.weightreductor.util.extensions.mergeSameProductsWeights
import com.spyrdonapps.weightreductor.util.extensions.removeEmptyProducts
import com.spyrdonapps.weightreductor.util.utils.localhostUrl
import com.spyrdonapps.weightreductor.util.utils.productionUrl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin(origins = [localhostUrl, productionUrl])
@RequestMapping("/api")
@RestController
class MealApi(private val mealRepository: MealRepository) {

    /*
    * todo
    *  not sure if binders are good here.
    *  I will get request, not a valid object
    *  probably to replace, also current validators are not good with REST API
    *  As well in Product/Weighing apis
    * */
    
    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("meal")
    fun initProductBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = MealValidator()
    }

    @GetMapping("/meals")
    fun getAllMeals(): MutableIterable<Meal> = mealRepository.findAll()

    @PostMapping("/meals/add")
    fun addMeal(@Valid meal: Meal, result: BindingResult): Any? =
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.allErrors.combinedMultilineError)
        } else {
            meal.removeEmptyProducts()
            meal.mergeSameProductsWeights()
            meal.productsWithWeights.forEach { it.meal = meal }
            mealRepository.save(meal)
        }
}