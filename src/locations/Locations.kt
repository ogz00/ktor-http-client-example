package com.oguz.thebase.locations

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location

const val API_HOME = "/"
const val API_CONVERT = "/convert"
const val API_DATE = "/{dateStr}"

@KtorExperimentalLocationsAPI
@Location(API_HOME)
class Latest

@KtorExperimentalLocationsAPI
@Location("/styles/main.css")
class MainCss

