package com.spyrdonapps.weightreductor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(proxyBeanMethods = false)
class WeightReductorApplication

fun main(args: Array<String>) {
    runApplication<WeightReductorApplication>(*args)
}