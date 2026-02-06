plugins {
    id("songlyst.kotlin.library")
    id("songlyst.android.library")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.foundation.itunes.fake"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.foundation.itunes.api)
            implementation(projects.core.di)
            implementation(libs.metro.runtime)
        }
    }
}
