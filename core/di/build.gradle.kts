plugins {
    id("songlyst.kotlin.library")
    id("songlyst.android.library")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.core.di"
    }
}
