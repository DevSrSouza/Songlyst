package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.Composable
import kotlin.reflect.KClass

public interface ScreenFactory<T : Route> {
    public val route: KClass<T>

    @Composable
    public fun Content(route: T)
}
