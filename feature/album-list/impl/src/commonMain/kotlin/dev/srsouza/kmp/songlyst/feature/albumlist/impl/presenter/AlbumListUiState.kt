package dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter

import dev.srsouza.kmp.songlyst.itunes.api.model.Album

public data class AlbumListUiState(
    val content: ContentState,
    val eventSink: (AlbumListEvent) -> Unit,
) {
    public sealed interface ContentState {
        public data object Loading : ContentState

        public data class Success(
            val albums: List<Album>,
            val eventSink: (ContentEvent) -> Unit,
        ) : ContentState

        public data class Error(
            val message: String,
        ) : ContentState
    }
}

public sealed interface AlbumListEvent {
    public data object OnRetryClicked : AlbumListEvent
}

public sealed interface ContentEvent {
    public data class OnAlbumClicked(
        val albumId: String,
    ) : ContentEvent
}
