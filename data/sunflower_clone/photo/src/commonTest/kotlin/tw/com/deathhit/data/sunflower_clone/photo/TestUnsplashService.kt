package tw.com.deathhit.data.sunflower_clone.photo

import tw.com.deathhit.core.unsplash.api_client.UnsplashService

abstract class TestUnsplashService: UnsplashService {
    override fun getAttributionUrl(userName: String): String = "FOO"
}