import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class SonglystFeatureImplPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("songlyst.compose.multiplatform")
                apply("dev.zacsweers.metro")
            }

            val compose = extensions.getByType<ComposeExtension>().dependencies

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(project(":core:di"))
                        implementation(project(":core:design"))
                        implementation(project(":core:navigation"))
                        implementation(project(":core:presenter"))
                        implementation(libs.findLibrary("lemonade-ui").get())
                        implementation(libs.findLibrary("lemonade-core").get())
                        implementation(libs.findLibrary("coil-compose").get())
                        implementation(libs.findLibrary("coil-network-ktor3").get())
                        implementation(libs.findLibrary("metro-runtime").get())
                        implementation(libs.findLibrary("compose-runtime-retain").get())
                        implementation(compose.material3)
                        implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                        implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                    }
                }
            }
        }
    }
}

private val Project.libs
    get() = extensions.getByType(org.gradle.api.artifacts.VersionCatalogsExtension::class.java)
        .named("libs")
