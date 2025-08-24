package tw.com.deathhit.data.photo.config

import tw.com.deathhit.core.unsplash.api_client.UnsplashService

abstract class TestUnsplashService: UnsplashService {
    override fun getAttributionUrl(userName: String): String {
        throw RuntimeException("Unused function for testing!")
    }
}