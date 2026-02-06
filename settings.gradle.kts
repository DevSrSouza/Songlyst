rootProject.name = "Songlyst"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":androidApp")
include(":shared")

include(":core:di")
include(":core:navigation")
include(":core:error-handling")
include(":core:network")
include(":core:design")
include(":core:presenter")

include(":foundation:itunes:api")
include(":foundation:itunes:impl")
include(":foundation:itunes:fake")

include(":feature:album-list:api")
include(":feature:album-list:impl")
include(":feature:album-detail:api")
include(":feature:album-detail:impl")

include(":integration-test")
