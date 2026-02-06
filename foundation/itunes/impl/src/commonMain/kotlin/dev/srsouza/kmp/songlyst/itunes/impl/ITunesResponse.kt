package dev.srsouza.kmp.songlyst.itunes.impl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ITunesResponse(
    val feed: Feed,
)

@Serializable
internal data class Feed(
    val entry: List<AlbumEntry>,
)

@Serializable
internal data class AlbumEntry(
    @SerialName("im:name")
    val name: Label,
    @SerialName("im:artist")
    val artist: Artist,
    @SerialName("im:image")
    val images: List<Image>,
    @SerialName("im:releaseDate")
    val releaseDate: ReleaseDate,
    val category: Category,
    val id: Id,
)

@Serializable
internal data class Label(
    val label: String,
)

@Serializable
internal data class Artist(
    val label: String,
)

@Serializable
internal data class Image(
    val label: String,
)

@Serializable
internal data class ReleaseDate(
    val label: String,
    val attributes: ReleaseDateAttributes,
)

@Serializable
internal data class ReleaseDateAttributes(
    val label: String,
)

@Serializable
internal data class Category(
    val attributes: CategoryAttributes,
)

@Serializable
internal data class CategoryAttributes(
    @SerialName("im:id")
    val id: String,
    val term: String,
    val label: String,
)

@Serializable
internal data class Id(
    val attributes: IdAttributes,
)

@Serializable
internal data class IdAttributes(
    @SerialName("im:id")
    val id: String,
)
