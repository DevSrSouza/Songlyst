package dev.srsouza.kmp.songlyst.feature.playground.impl

import androidx.compose.runtime.Composable
import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.playground.api.PlaygroundRoute
import dev.srsouza.kmp.songlyst.feature.playground.impl.presenter.PlaygroundPresenter
import dev.srsouza.kmp.songlyst.feature.playground.impl.presenter.PlaygroundUiState
import dev.srsouza.kmp.songlyst.feature.playground.impl.ui.PlaygroundScreen
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.srsouza.kmp.songlyst.presenter.Presenter
import dev.zacsweers.metro.ContributesIntoSet
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.binding
import kotlin.reflect.KClass

@ContributesIntoSet(AppScope::class, binding = binding<ScreenFactory<*, *>>())
public class PlaygroundScreenFactory
    @Inject
    constructor(
        private val presenterProvider: Provider<PlaygroundPresenter>,
    ) : ScreenFactory<PlaygroundRoute, PlaygroundUiState> {
        override val route: KClass<PlaygroundRoute> = PlaygroundRoute::class

        override fun createPresenter(route: PlaygroundRoute): Presenter<PlaygroundUiState> =
            presenterProvider()

        @Composable
        override fun Content(state: PlaygroundUiState) {
            PlaygroundScreen(state = state)
        }
    }
