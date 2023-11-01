pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Sunflower Clone"
include(":app")
include(":core:app_database")
include(":core:app_ui")
include(":domain")
include(":core:unsplash_api")
include(":data:plant")
include(":data:garden_planting")
