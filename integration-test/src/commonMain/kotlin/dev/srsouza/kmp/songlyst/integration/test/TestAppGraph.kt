package dev.srsouza.kmp.songlyst.integration.test

import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides

@DependencyGraph(AppScope::class)
public interface TestAppGraph {
    public val screenFactories: Set<ScreenFactory<*, *>>
    public val repository: ITunesRepository

    @DependencyGraph.Factory
    public interface Factory {
        public fun create(
            @Provides navigator: Navigator,
        ): TestAppGraph
    }
}
