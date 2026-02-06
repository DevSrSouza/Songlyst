plugins {
    id("songlyst.feature.impl")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.feature.albumdetail.impl"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.albumDetail.api)
            implementation(projects.foundation.itunes.api)
            implementation(projects.core.navigation)
        }
    }
}
