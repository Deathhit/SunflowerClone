package tw.com.deathhit.core.unsplash_api.protocol

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class UnsplashAuthenticator(private val onGetApiKeyCallback: () -> String) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? =
        response.request().newBuilder()
            .addHeader("Authorization:Client-ID", onGetApiKeyCallback())
            .build()
}