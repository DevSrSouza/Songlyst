package dev.srsouza.kmp.songlyst.itunes.impl

import dev.srsouza.kmp.songlyst.di.AppScope
import dev.srsouza.kmp.songlyst.errorhandling.safeRunCatching
import dev.srsouza.kmp.songlyst.itunes.api.ITunesRepository
import dev.srsouza.kmp.songlyst.itunes.api.model.Album
import dev.srsouza.kmp.songlyst.network.toNetworkError
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@ContributesBinding(AppScope::class)
public class ITunesRepositoryImpl
    @Inject
    internal constructor(
        private val apiService: ITunesApiService,
    ) : ITunesRepository {
        private val cachedAlbums = MutableStateFlow<Result<Map<String, Album>>?>(null)

        override fun getAlbums(): Flow<Result<List<Album>>> =
            cachedAlbums
                .onStart { if (cachedAlbums.value == null) fetchAlbums() }
                .filterNotNull()
                .map { result -> result.map { it.values.toList() } }

        override suspend fun refresh(): Result<Unit> = fetchAlbums().map {}

        override suspend fun getAlbumById(id: String): Result<Album> {
            cachedAlbums.value
                ?.getOrNull()
                ?.get(id)
                ?.let { return Result.success(it) }

            return fetchAlbums().map { albums ->
                albums[id] ?: throw AlbumNotFoundException(id)
            }
        }

        private suspend fun fetchAlbums(): Result<Map<String, Album>> =
            safeRunCatching {
                val response = apiService.getTopAlbums()
                val albums = response.feed.entry.toDomain()
                albums.associateBy { it.id }
            }.fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it.toNetworkError()) },
            ).also { cachedAlbums.value = it }
    }

public class AlbumNotFoundException(
    albumId: String,
) : Exception("Album not found: $albumId")
