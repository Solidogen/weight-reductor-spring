package com.spyrdonapps.weightreductor.controller.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CrashController {

    @GetMapping("/crash")
    fun crash() {
        error("Crash pls")
    }
}