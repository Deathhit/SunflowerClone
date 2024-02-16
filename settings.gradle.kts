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
include(":app_compose")
include(":app_navigation")
include(":core:app_database")
include(":core:app_ui")
include(":core:app_ui_compose")
include(":core:unsplash_api")
include(":data:garden_planting")
include(":data:photo")
include(":data:plant")
include(":domain")
include(":feature:compose:garden_planting_list")
include(":feature:compose:navigation")
include(":feature:compose:plant_details")
include(":feature:compose:plant_list")
include(":feature:gallery")
include(":feature:garden_planting_list")
include(":feature:navigation")
include(":feature:plant_details")
include(":feature:plant_list")