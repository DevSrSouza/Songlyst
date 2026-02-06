plugins {
    id("songlyst.compose.multiplatform")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.core.presenter"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime.retain)
        }
    }
}
