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
include(":app_sunflower_clone")
include(":app_sunflower_clone_compose")
include(":app_sunflower_clone_navigation")
include(":config:sunflower_clone")
include(":core:sunflower_clone_database")
include(":core:sunflower_clone_ui")
include(":core:app_ui_compose")
include(":core:unsplash_api")
include(":data:garden_planting")
include(":data:photo")
include(":data:plant")
include(":domain")
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

