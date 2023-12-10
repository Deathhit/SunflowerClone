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
include(":core:app_ui_compose")
include(":core:unsplash_api")
include(":data:garden_planting")
include(":data:photo")
include(":data:plant")
include(":domain")
include(":feature:gallery")
include(":feature:garden_planting_list")
include(":feature:navigation")
include(":feature:plant_details")
include(":feature:plant_details_compose")
include(":feature:plant_list")