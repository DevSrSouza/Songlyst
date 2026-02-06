package dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import dev.srsouza.kmp.songlyst.errorhandling.NetworkError
import dev.srsouza.kmp.songlyst.feature.albumdetail.api.AlbumDetailRoute
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.srsouza.kmp.songlyst.presenter.collectAsRetainedState
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.launch

public class AlbumListPresenter
    @Inject
    constructor(
        private val navigator: Navigator,
        private val repository: ITunesRepository,
    ) {
        @Composable
        public fun present(): AlbumListUiState {
            val albumsResult by repository.getAlbums().collectAsRetainedState(initial = null)
            val scope = rememberCoroutineScope()

            val eventSink: (AlbumListEvent) -> Unit = { event ->
                when (event) {
                    AlbumListEvent.OnRetryClicked -> scope.launch { repository.refresh() }
                }
            }

            val contentEventSink: (ContentEvent) -> Unit = { event ->
                when (event) {
                    is ContentEvent.OnAlbumClicked ->
                        navigator.navigate(AlbumDetailRoute(event.albumId))
                }
            }

            val content =
                when (val result = albumsResult) {
                    null -> AlbumListUiState.ContentState.Loading
                    else ->
                        result.fold(
                            onSuccess = { albums ->
                                AlbumListUiState.ContentState.Success(
                                    albums = albums,
                                    eventSink = contentEventSink,
                                )
                            },
                            onFailure = { error ->
                                AlbumListUiState.ContentState.Error(message = error.toErrorMessage())
                            },
                        )
                }

            return AlbumListUiState(
                content = content,
                eventSink = eventSink,
            )
        }

        private fun Throwable.toErrorMessage(): String =
            when (this) {
                is NetworkError -> toUserMessage()
                else -> "Something went wrong. Please try again."
            }
    }
