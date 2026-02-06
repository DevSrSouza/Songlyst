plugins {
    id("songlyst.compose.multiplatform")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.core.design"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.lemonade.ui)
            api(libs.lemonade.core)
        }
    }
}
