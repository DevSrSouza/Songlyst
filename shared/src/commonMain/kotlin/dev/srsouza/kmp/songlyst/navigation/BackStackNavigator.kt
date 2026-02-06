package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.albumlist.api.AlbumListRoute
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
public class BackStackNavigator
    @Inject
    constructor() : NavigationStack {
        public val startDestination: Route = AlbumListRoute
        override val backStack: SnapshotStateList<Route> = mutableStateListOf(startDestination)

        override fun navigate(route: Route) {
            backStack.add(route)
        }

        override fun popBackStack() {
            if (backStack.size > 1) {
                backStack.removeLast()
            }
        }
    }
