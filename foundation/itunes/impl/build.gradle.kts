plugins {
    id("songlyst.foundation.impl")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.foundation.itunes.impl"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.foundation.itunes.api)
            implementation(projects.core.network)
            implementation(projects.core.errorHandling)
        }
        commonTest.dependencies {
            implementation(libs.kotest.assertions.core)
        }
    }
}
