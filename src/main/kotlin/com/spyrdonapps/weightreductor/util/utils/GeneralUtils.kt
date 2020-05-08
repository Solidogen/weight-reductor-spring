package com.spyrdonapps.weightreductor.util.utils

val required
    get() = "required"

fun Float?.orZero() = this ?: 0f