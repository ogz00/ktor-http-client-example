package com.oguz.thebase.data

import java.io.Serializable

data class Info(
    val rates: Map<String, String>,
    val base: String,
    val date: String
) : Serializable


fun Info.getRatesAsList() = rates.toList().map { "${it.first}, ${it.second}" }

fun Info.getRates(): MutableList<Rate> {
    val list = mutableListOf<Rate>()
    for ((key, value) in rates) {
        val rate = Rate(key, value)
        list.add(rate)
    }
    return list
}