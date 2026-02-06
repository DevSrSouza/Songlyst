package dev.srsouza.kmp.songlyst.itunes.impl

import dev.zacsweers.metro.Inject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ITunesApiService
    @Inject
    constructor(
        private val httpClient: HttpClient,
    ) {
        suspend fun getTopAlbums(): ITunesResponse = httpClient.get(TOP_ALBUMS_URL).body()

        private companion object {
            private const val TOP_ALBUMS_URL = "https://itunes.apple.com/us/rss/topalbums/limit=100/json"
        }
    }
