plugins {
    id("songlyst.feature.impl")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.feature.playground.impl"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.playground.api)
            implementation(projects.core.navigation)
        }
    }
}
