package dev.srsouza.kmp.songlyst.itunes.impl

import dev.srsouza.kmp.songlyst.itunes.api.model.Album

internal fun AlbumEntry.toDomain(): Album =
    Album(
        id = id.attributes.id,
        name = name.label,
        artistName = artist.label,
        artworkUrl = images.lastOrNull()?.label.orEmpty(),
        genre = category.attributes.label,
        releaseDate = releaseDate.attributes.label,
    )

internal fun List<AlbumEntry>.toDomain(): List<Album> = map { it.toDomain() }
