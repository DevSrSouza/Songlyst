plugins {
    id("songlyst.kotlin.library")
    id("songlyst.android.library")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.core.network"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.serialization.kotlinx.json)
            api(libs.ktor.client.logging)
            api(projects.core.errorHandling)
            implementation(projects.core.di)
            implementation(libs.metro.runtime)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
