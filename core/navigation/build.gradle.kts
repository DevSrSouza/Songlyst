plugins {
    id("songlyst.compose.multiplatform")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.core.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.navigation3.runtime)
        }
    }
}
