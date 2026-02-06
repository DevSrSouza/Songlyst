package dev.srsouza.kmp.songlyst.feature.albumdetail.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.albumdetail.api.AlbumDetailRoute
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailPresenter
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.ui.AlbumDetailScreen
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.zacsweers.metro.ContributesIntoSet
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import kotlin.reflect.KClass

@ContributesIntoSet(AppScope::class, binding = binding<ScreenFactory<*>>())
public class AlbumDetailScreenFactory
    @Inject
    constructor(
        private val presenterFactory: AlbumDetailPresenter.Factory,
    ) : ScreenFactory<AlbumDetailRoute> {
        override val route: KClass<AlbumDetailRoute> = AlbumDetailRoute::class

        @Composable
        override fun Content(route: AlbumDetailRoute) {
            val presenter = retain {
                presenterFactory.create(route.albumId)
            }

            val state = presenter.present()

            AlbumDetailScreen(state = state)
        }
    }
