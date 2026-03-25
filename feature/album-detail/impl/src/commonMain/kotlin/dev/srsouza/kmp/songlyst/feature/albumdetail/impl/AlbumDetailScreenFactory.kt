package dev.srsouza.kmp.songlyst.feature.albumdetail.impl

import androidx.compose.runtime.Composable
import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.albumdetail.api.AlbumDetailRoute
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailPresenter
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailUiState
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.ui.AlbumDetailScreen
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.srsouza.kmp.songlyst.presenter.Presenter
import dev.zacsweers.metro.ContributesIntoSet
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import kotlin.reflect.KClass

@ContributesIntoSet(AppScope::class, binding = binding<ScreenFactory<*, *>>())
public class AlbumDetailScreenFactory
    @Inject
    constructor(
        private val presenterFactory: AlbumDetailPresenter.Factory,
    ) : ScreenFactory<AlbumDetailRoute, AlbumDetailUiState> {
        override val route: KClass<AlbumDetailRoute> = AlbumDetailRoute::class

        override fun createPresenter(route: AlbumDetailRoute): Presenter<AlbumDetailUiState> =
            presenterFactory.create(route.albumId)

        @Composable
        override fun Content(state: AlbumDetailUiState) {
            AlbumDetailScreen(state = state)
        }
    }
