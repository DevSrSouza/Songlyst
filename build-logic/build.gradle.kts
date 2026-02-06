plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
    compileOnly(libs.metro.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinLibrary") {
            id = "songlyst.kotlin.library"
            implementationClass = "SonglystKotlinLibraryPlugin"
        }
        register("androidLibrary") {
            id = "songlyst.android.library"
            implementationClass = "SonglystAndroidLibraryPlugin"
        }
        register("composeMultiplatform") {
            id = "songlyst.compose.multiplatform"
            implementationClass = "SonglystComposeMultiplatformPlugin"
        }
        register("foundationApi") {
            id = "songlyst.foundation.api"
            implementationClass = "SonglystFoundationApiPlugin"
        }
        register("foundationImpl") {
            id = "songlyst.foundation.impl"
            implementationClass = "SonglystFoundationImplPlugin"
        }
        register("featureApi") {
            id = "songlyst.feature.api"
            implementationClass = "SonglystFeatureApiPlugin"
        }
        register("featureImpl") {
            id = "songlyst.feature.impl"
            implementationClass = "SonglystFeatureImplPlugin"
        }
    }
}
