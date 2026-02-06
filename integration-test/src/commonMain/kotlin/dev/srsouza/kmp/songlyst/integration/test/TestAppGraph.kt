package dev.srsouza.kmp.songlyst.integration.test

import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailPresenter
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListPresenter
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides

@DependencyGraph(AppScope::class)
public interface TestAppGraph {
    public fun createAlbumListPresenter(): AlbumListPresenter

    public val albumDetailPresenterFactory: AlbumDetailPresenter.Factory
    public val repository: ITunesRepository

    @DependencyGraph.Factory
    public interface Factory {
        public fun create(
            @Provides navigator: Navigator,
        ): TestAppGraph
    }
}
