pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
include(":app:internal:sunflower_clone_config")
include(":app:sunflower_clone")
include(":app:sunflower_clone_compose")
include(":app:sunflower_clone_navigation")
include(":core:sunflower_clone:app_database")
include(":core:sunflower_clone_ui")
include(":core:unsplash:api_client")
include(":data:sunflower_clone:garden_planting")
include(":data:sunflower_clone:photo")
include(":data:sunflower_clone:plant")
include(":domain:sunflower_clone")
include(":feature:compose:gallery")
include(":feature:compose:garden_planting_list")
include(":feature:compose:navigation")
include(":feature:compose:plant_details")
include(":feature:compose:plant_list")
include(":feature:gallery")
include(":feature:garden_planting_list")
include(":feature:navigation")
include(":feature:plant_details")
include(":feature:plant_list")
