package dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.itunes.api.model.Album
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.srsouza.kmp.songlyst.presenter.produceRetainedState
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

public class AlbumDetailPresenter
    @AssistedInject
    constructor(
        @Assisted private val albumId: String,
        private val navigator: Navigator,
        private val repository: ITunesRepository,
    ) {
        @Composable
        public fun present(): AlbumDetailUiState {
            val albumResult by produceRetainedState<Result<Album>?>(initialValue = null) {
                value = repository.getAlbumById(albumId)
            }

            val eventSink: (AlbumDetailEvent) -> Unit = { event ->
                when (event) {
                    AlbumDetailEvent.OnBackClicked -> navigator.popBackStack()
                }
            }

            val content =
                when (val result = albumResult) {
                    null -> AlbumDetailUiState.ContentState.Loading
                    else ->
                        result.fold(
                            onSuccess = { album ->
                                AlbumDetailUiState.ContentState.Success(album = album)
                            },
                            onFailure = {
                                AlbumDetailUiState.ContentState.Error(
                                    message = "Album not found. Please go back and try again.",
                                )
                            },
                        )
                }

            return AlbumDetailUiState(
                content = content,
                eventSink = eventSink,
            )
        }

        @AssistedFactory
        public fun interface Factory {
            public fun create(albumId: String): AlbumDetailPresenter
        }
    }
