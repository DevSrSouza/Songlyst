package dev.srsouza.kmp.songlyst.itunes.fake

import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.itunes.api.model.Album
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
public class FakeITunesRepository
    @Inject
    constructor() : ITunesRepository {
        private val albumsFlow = MutableStateFlow<Result<List<Album>>?>(null)

        override fun getAlbums(): Flow<Result<List<Album>>> = albumsFlow.filterNotNull()

        override suspend fun getAlbumById(id: String): Result<Album> {
            val albums =
                albumsFlow.value?.getOrNull()
                    ?: return Result.failure(Exception("No albums loaded"))

            val album =
                albums.find { it.id == id }
                    ?: return Result.failure(Exception("Album not found: $id"))

            return Result.success(album)
        }

        public var onRefresh: (suspend () -> Unit)? = null

        override suspend fun refresh(): Result<Unit> {
            onRefresh?.invoke()
            return Result.success(Unit)
        }

        public fun emitAlbums(albums: List<Album>) {
            albumsFlow.value = Result.success(albums)
        }

        public fun emitError(error: Throwable) {
            albumsFlow.value = Result.failure(error)
        }
    }
