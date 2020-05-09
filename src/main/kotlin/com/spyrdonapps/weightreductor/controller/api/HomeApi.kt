package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.response.HomeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class HomeApi {

    @GetMapping("/home")
    fun getHome() = HomeResponse(name = "Home")
}
