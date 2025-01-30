package tw.com.deathhit.app.sunflower_clone_kmp_config

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

internal fun Context.getUnsplashAccessKey() =
    getMetadataString("tw.com.deathhit.sunflower_clone.UNSPLASH_ACCESS_KEY")

internal fun Context.getUnsplashAppName() =
    getMetadataString("tw.com.deathhit.sunflower_clone.UNSPLASH_APP_NAME")

internal fun Context.getUnsplashServerUrl() =
    getMetadataString("tw.com.deathhit.sunflower_clone.UNSPLASH_SERVER_URL")

private fun Context.getMetadataString(key: String) = with(packageManager) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(
            packageName,
            PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA.toLong())
        )
    } else {
        getPackageInfo(
            packageName,
            PackageManager.GET_META_DATA
        )
    }
}.applicationInfo?.metaData?.getString(key)!!