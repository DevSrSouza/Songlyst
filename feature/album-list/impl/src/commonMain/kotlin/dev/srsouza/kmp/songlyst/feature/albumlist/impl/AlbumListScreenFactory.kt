package dev.srsouza.kmp.songlyst.feature.albumlist.impl

import androidx.compose.runtime.Composable
import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.albumlist.api.AlbumListRoute
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListPresenter
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListUiState
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.AlbumListScreen
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.srsouza.kmp.songlyst.presenter.Presenter
import dev.zacsweers.metro.ContributesIntoSet
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.binding
import kotlin.reflect.KClass

@ContributesIntoSet(AppScope::class, binding = binding<ScreenFactory<*, *>>())
public class AlbumListScreenFactory
    @Inject
    constructor(
        private val presenterProvider: Provider<AlbumListPresenter>,
    ) : ScreenFactory<AlbumListRoute, AlbumListUiState> {
        override val route: KClass<AlbumListRoute> = AlbumListRoute::class

        override fun createPresenter(route: AlbumListRoute): Presenter<AlbumListUiState> =
            presenterProvider()

        @Composable
        override fun Content(state: AlbumListUiState) {
            AlbumListScreen(state = state)
        }
    }
