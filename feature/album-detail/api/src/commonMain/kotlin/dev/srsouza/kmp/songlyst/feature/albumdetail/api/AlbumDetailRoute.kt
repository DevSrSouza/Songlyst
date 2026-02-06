package dev.srsouza.kmp.songlyst.feature.albumdetail.api

import dev.srsouza.kmp.songlyst.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
public data class AlbumDetailRoute(
    public val albumId: String,
) : Route
