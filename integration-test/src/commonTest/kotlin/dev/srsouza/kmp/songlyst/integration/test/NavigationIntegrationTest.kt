package dev.srsouza.kmp.songlyst.integration.test

import dev.srsouza.kmp.songlyst.errorhandling.NetworkError
import dev.srsouza.kmp.songlyst.feature.albumdetail.api.AlbumDetailRoute
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailEvent
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailUiState
import dev.srsouza.kmp.songlyst.feature.albumlist.api.AlbumListRoute
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListEvent
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListUiState
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.ContentEvent
import dev.srsouza.kmp.songlyst.itunes.fake.FakeAlbumData
import dev.srsouza.kmp.songlyst.itunes.fake.FakeITunesRepository
import dev.zacsweers.metro.createGraphFactory
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class NavigationIntegrationTest {
    private fun createTestGraph(navigator: FakeNavigator): TestAppGraph = createGraphFactory<TestAppGraph.Factory>().create(navigator)

    private fun FakeNavigator.registerRoutes(graph: TestAppGraph) {
        onRoute<AlbumListRoute> { _ ->
            graph.createAlbumListPresenter().present()
        }
        onRoute<AlbumDetailRoute> { route ->
            graph.albumDetailPresenterFactory.create(route.albumId).present()
        }
    }

    @Test
    fun navigateFromAlbumListToAlbumDetailAndBack() =
        runTest {
            val navigator = FakeNavigator(parentScope = backgroundScope)
            val graph = createTestGraph(navigator)
            val fakeRepo = graph.repository as FakeITunesRepository

            navigator.registerRoutes(graph)

            fakeRepo.emitAlbums(listOf(FakeAlbumData.album1, FakeAlbumData.album2))
            navigator.start(AlbumListRoute)
            navigator.backStackSize shouldBe 1

            val listState =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Success
                }
            val success = listState.content as AlbumListUiState.ContentState.Success
            success.albums shouldHaveSize 2
            success.albums[0] shouldBe FakeAlbumData.album1
            success.albums[1] shouldBe FakeAlbumData.album2

            success.eventSink(ContentEvent.OnAlbumClicked(FakeAlbumData.album1.id))
            navigator.backStackSize shouldBe 2

            val detailState =
                navigator.awaitState<AlbumDetailUiState> {
                    it.content is AlbumDetailUiState.ContentState.Success
                }
            val detailSuccess = detailState.content as AlbumDetailUiState.ContentState.Success
            detailSuccess.album shouldBe FakeAlbumData.album1

            detailState.eventSink(AlbumDetailEvent.OnBackClicked)
            navigator.backStackSize shouldBe 1

            val backState = navigator.currentState<AlbumListUiState>()
            val backContent = backState.content.shouldBeInstanceOf<AlbumListUiState.ContentState.Success>()
            backContent.albums shouldHaveSize 2
            backContent.albums.first() shouldBe FakeAlbumData.album1
        }

    @Test
    fun albumListShowsErrorWhenLoadFails() =
        runTest {
            val navigator = FakeNavigator(parentScope = backgroundScope)
            val graph = createTestGraph(navigator)
            val fakeRepo = graph.repository as FakeITunesRepository

            navigator.registerRoutes(graph)

            fakeRepo.emitError(NetworkError.NoConnection())
            navigator.start(AlbumListRoute)
            navigator.backStackSize shouldBe 1

            val state =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Error
                }
            val error = state.content as AlbumListUiState.ContentState.Error
            error.message shouldBe NetworkError.NoConnection().toUserMessage()

            fakeRepo.emitAlbums(listOf(FakeAlbumData.album1, FakeAlbumData.album2))

            val recoveredState =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Success
                }
            val success = recoveredState.content as AlbumListUiState.ContentState.Success
            success.albums shouldHaveSize 2
            success.albums.first() shouldBe FakeAlbumData.album1
        }

    @Test
    fun albumListRetryClickRecoverFromError() =
        runTest {
            val navigator = FakeNavigator(parentScope = backgroundScope)
            val graph = createTestGraph(navigator)
            val fakeRepo = graph.repository as FakeITunesRepository

            navigator.registerRoutes(graph)

            fakeRepo.emitError(NetworkError.NoConnection())
            navigator.start(AlbumListRoute)

            val errorState =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Error
                }
            val error = errorState.content as AlbumListUiState.ContentState.Error
            error.message shouldBe NetworkError.NoConnection().toUserMessage()

            fakeRepo.onRefresh = {
                fakeRepo.emitAlbums(listOf(FakeAlbumData.album1, FakeAlbumData.album2))
            }

            errorState.eventSink(AlbumListEvent.OnRetryClicked)

            val recoveredState =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Success
                }
            val success = recoveredState.content as AlbumListUiState.ContentState.Success
            success.albums shouldHaveSize 2
            success.albums[0] shouldBe FakeAlbumData.album1
            success.albums[1] shouldBe FakeAlbumData.album2
        }

    @Test
    fun albumDetailShowsErrorForInvalidAlbumId() =
        runTest {
            val navigator = FakeNavigator(parentScope = backgroundScope)
            val graph = createTestGraph(navigator)
            val fakeRepo = graph.repository as FakeITunesRepository

            navigator.registerRoutes(graph)

            fakeRepo.emitAlbums(listOf(FakeAlbumData.album1, FakeAlbumData.album2))
            navigator.start(AlbumListRoute)

            val listState =
                navigator.awaitState<AlbumListUiState> {
                    it.content is AlbumListUiState.ContentState.Success
                }
            val success = listState.content as AlbumListUiState.ContentState.Success
            success.albums shouldHaveSize 2

            navigator.navigate(AlbumDetailRoute(albumId = "bad-id"))
            navigator.backStackSize shouldBe 2

            val detailState =
                navigator.awaitState<AlbumDetailUiState> {
                    it.content is AlbumDetailUiState.ContentState.Error
                }

            detailState.eventSink(AlbumDetailEvent.OnBackClicked)
            navigator.backStackSize shouldBe 1

            val backState = navigator.currentState<AlbumListUiState>()
            backState.content.shouldBeInstanceOf<AlbumListUiState.ContentState.Success>()
        }
}
