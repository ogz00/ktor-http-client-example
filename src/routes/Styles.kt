package io.ktor.samples.kweet

import com.oguz.thebase.locations.MainCss
import io.ktor.application.call
import io.ktor.http.content.resolveResource
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

/**
 * Register the styles, [MainCss] route (/styles/main.css)
 */
@KtorExperimentalLocationsAPI
fun Route.styles() {
    /**
     * On a GET request to the [MainCss] route, it returns the `blog.css` file from the resources.
     *
     * Here we could preprocess or join several CSS/SASS/LESS.
     */
    get<MainCss> {
        call.respond(call.resolveResource("style.css")!!)
    }
}
