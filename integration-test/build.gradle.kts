plugins {
    id("songlyst.compose.multiplatform")
    id("dev.zacsweers.metro")
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.integration.test"

        withHostTestBuilder {}.configure {
            isReturnDefaultValues = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.albumList.impl)
            implementation(projects.feature.albumDetail.impl)
            implementation(projects.foundation.itunes.fake)

            implementation(projects.core.di)
            implementation(projects.core.navigation)
            implementation(projects.foundation.itunes.api)
            implementation(libs.metro.runtime)
        }
        commonTest.dependencies {
            implementation(projects.core.navigation)
            implementation(projects.core.errorHandling)
            implementation(projects.foundation.itunes.api)
            implementation(libs.molecule.runtime)
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
            implementation(libs.kotest.assertions.core)
        }
    }
}

tasks.withType<Test> {
    testLogging {
        showStandardStreams = true
    }
}
