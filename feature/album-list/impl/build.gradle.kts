plugins {
    id("songlyst.feature.impl")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.feature.albumlist.impl"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.albumList.api)
            implementation(projects.feature.albumDetail.api)
            implementation(projects.foundation.itunes.api)
            implementation(projects.core.navigation)
            implementation(projects.core.errorHandling)
        }
        commonTest.dependencies {
            implementation(libs.kotest.assertions.core)
            implementation(libs.turbine)
        }
    }
}
