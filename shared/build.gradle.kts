plugins {
    id("songlyst.compose.multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
}

kotlin {
    androidLibrary {
        namespace = "dev.srsouza.kmp.songlyst.shared"
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            export(projects.foundation.itunes.api)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.foundation.itunes.api)
            implementation(projects.foundation.itunes.impl)
            implementation(projects.core.di)
            implementation(projects.core.navigation)
            implementation(projects.core.network)
            implementation(projects.core.design)
            implementation(projects.feature.albumList.api)
            implementation(projects.feature.albumList.impl)
            implementation(projects.feature.albumDetail.api)
            implementation(projects.feature.albumDetail.impl)

            implementation(libs.navigation3.ui)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
            implementation(compose.material3)
            implementation(libs.lemonade.ui)
        }
    }
}
