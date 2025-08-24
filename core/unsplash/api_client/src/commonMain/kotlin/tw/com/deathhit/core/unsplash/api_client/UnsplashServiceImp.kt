package tw.com.deathhit.core.unsplash.api_client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.response.SearchPhotosResponse

class UnsplashServiceImp(
    private val accessKey: String,
    private val serverUrl: String,
    private val unsplashAppName: String
) : UnsplashService {
    private val client = createHttpClient()

    override fun getAttributionUrl(userName: String): String =
        "https://unsplash.com/$userName?utm_source=$unsplashAppName&utm_medium=referral"

    override suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoApiEntity> = client.get {
        url {
            path("search/photos")
            parameter("page", page)
            parameter("per_page", perPage)
            parameter("query", query)
        }
    }.body<SearchPhotosResponse>().results

    private fun createHttpClient() = HttpClient {
        expectSuccess = true

        defaultRequest {
            header(HttpHeaders.Authorization, "Client-ID $accessKey")
            url(urlString = serverUrl)
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        //todo doesn't seems to be logging anything
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }
}