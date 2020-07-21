package com.spyrdonapps.weightreductor.controller.api

import com.spyrdonapps.weightreductor.model.response.HomeResponse
import com.spyrdonapps.weightreductor.util.utils.localhostUrl
import com.spyrdonapps.weightreductor.util.utils.productionUrl
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = [localhostUrl, productionUrl])
@RequestMapping("/api")
@RestController
class HomeApi {

    @GetMapping("/home")
    fun getHome() = HomeResponse(name = "Home")
}
