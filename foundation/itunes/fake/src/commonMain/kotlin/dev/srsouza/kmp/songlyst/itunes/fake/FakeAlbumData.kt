package dev.srsouza.kmp.songlyst.itunes.fake

import dev.srsouza.kmp.songlyst.itunes.api.model.Album

public object FakeAlbumData {
    public val album1: Album =
        Album(
            id = "123456789",
            name = "Test Album 1",
            artistName = "Test Artist 1",
            artworkUrl = "https://fake.com/image.jpg",
            genre = "Rock",
            releaseDate = "January 16, 2024",
        )

    public val album2: Album =
        Album(
            id = "987654321",
            name = "Test Album 2",
            artistName = "Test Artist 2",
            artworkUrl = "https://fake.com/image_2.jpg",
            genre = "Rock",
            releaseDate = "January 20, 2024",
        )
}
