package com.spyrdonapps.weightreductor.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun welcome(): String = "home/home"
}