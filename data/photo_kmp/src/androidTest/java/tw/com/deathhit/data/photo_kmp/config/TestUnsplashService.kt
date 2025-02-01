package tw.com.deathhit.data.photo_kmp.config

import tw.com.deathhit.core.unsplash_api.UnsplashService

abstract class TestUnsplashService: UnsplashService {
    override fun getAttributionUrl(userName: String): String {
        throw RuntimeException("Unused function for testing!")
    }
}