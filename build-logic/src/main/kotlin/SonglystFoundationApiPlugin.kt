import org.gradle.api.Plugin
import org.gradle.api.Project

class SonglystFoundationApiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("songlyst.kotlin.library")
                apply("songlyst.android.library")
            }
        }
    }
}
