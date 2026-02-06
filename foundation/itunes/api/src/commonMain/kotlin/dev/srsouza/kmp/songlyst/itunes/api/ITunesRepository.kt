package dev.srsouza.kmp.songlyst.itunes.api

import dev.srsouza.kmp.songlyst.itunes.api.model.Album
import kotlinx.coroutines.flow.Flow

public interface ITunesRepository {
    public fun getAlbums(): Flow<Result<List<Album>>>

    public suspend fun getAlbumById(id: String): Result<Album>

    public suspend fun refresh(): Result<Unit>
}
