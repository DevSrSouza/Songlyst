package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.Composable
import dev.srsouza.kmp.songlyst.presenter.Presenter
import kotlin.reflect.KClass

public interface ScreenFactory<T : Route, S : Any> {
    public val route: KClass<T>

    public fun createPresenter(route: T): Presenter<S>

    @Composable
    public fun Content(state: S)
}
