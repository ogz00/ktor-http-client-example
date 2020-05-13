package com.oguz.thebase.routes

import com.oguz.thebase.data.Converter
import com.oguz.thebase.data.Info
import com.oguz.thebase.data.Rate
import com.oguz.thebase.data.getRates
import com.oguz.thebase.locations.Latest
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.queryString
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.util.KtorExperimentalAPI

const val EXCHANGE_ROOT = "https://api.exchangeratesapi.io"
const val EXCHANGE_LATEST = "$EXCHANGE_ROOT/latest"

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
fun Routing.home(client: HttpClient) {
    get<Latest> {
        try {
            val response: Info = client.get("$EXCHANGE_LATEST?${call.request.queryString()}")
            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    mapOf(
                        "info" to response,
                        "rateList" to response.getRates() as ArrayList<Rate>,
                        "converter" to  Converter(0.0, 0.0, "", "")
                    )
                )
            )
        } catch (ex: Exception) {
            ex.message?.let { it1 -> call.respond(it1) }
        } finally {
        }
    }

    post<Latest> {
        try {
            val post = call.receive<Parameters>()
            val amount = post["amount"] ?: throw Exception("Amount can not be empty")
            val source = post["source"] ?: throw Exception("Source can not be empty")
            val target = post["target"] ?: throw Exception("Target can not be empty")
            val baseUrl = "$EXCHANGE_LATEST?base=${source}"
            val response: Info = client.get(baseUrl)
            val currency: Double = response.rates[target]?.toDouble() ?: 0.0
            val res = currency * amount.toDouble()
            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    mapOf(
                        "info" to response,
                        "rateList" to response.getRates() as ArrayList<Rate>,
                        "converter" to Converter(amount.toDouble(), res, source, target)
                    )
                )
            )
        } catch (ex: Exception) {
            ex.message?.let { it1 -> call.respond(it1) }
        } finally {
        }
    }

}