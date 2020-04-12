package com.meslmawy.covid19_notifier_eg.model


data class Case(
    val new : String?,
    val active : Int = 0,
    val critical : Int = 0,
    val recovered : Int = 0,
    val total : Int = 0
)

data class Death(
    val new : String? = "0",
    val total : Int = 0
)