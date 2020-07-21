package com.spyrdonapps.weightreductor.util.utils

const val required = "required"
const val localhostUrl = "http://localhost:3000"
const val productionUrl = "https://weightreductor.netlify.app/"

fun Float?.orZero() = this ?: 0f