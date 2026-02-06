package dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter

import dev.srsouza.kmp.songlyst.itunes.api.model.Album

public data class AlbumDetailUiState(
    val content: ContentState,
    val eventSink: (AlbumDetailEvent) -> Unit,
) {
    public sealed interface ContentState {
        public data object Loading : ContentState

        public data class Success(
            val album: Album,
        ) : ContentState

        public data class Error(
            val message: String,
        ) : ContentState
    }
}

public sealed interface AlbumDetailEvent {
    public data object OnBackClicked : AlbumDetailEvent
}
