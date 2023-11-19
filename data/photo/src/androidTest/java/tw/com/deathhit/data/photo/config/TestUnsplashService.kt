package tw.com.deathhit.data.photo.config

import tw.com.deathhit.core.unsplash_api.UnsplashService

abstract class TestUnsplashService: UnsplashService {
    override fun getAttributionUrl(authorId: String): String {
        throw RuntimeException("Unused function for testing!")
    }
}