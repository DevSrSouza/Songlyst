package dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.teya.lemonade.LemonadeTheme
import com.teya.lemonade.LemonadeUi
import com.teya.lemonade.Text
import dev.srsouza.kmp.songlyst.itunes.api.model.Album

@Composable
internal fun AlbumList(
    albums: List<Album>,
    onAlbumClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing200),
    ) {
        itemsIndexed(
            items = albums,
            key = { _, album -> album.id },
        ) { index, album ->
            AlbumListItem(
                album = album,
                index = index,
                onClick = { onAlbumClick(album.id) },
            )
        }
    }
}

@Composable
private fun AlbumListItem(
    album: Album,
    index: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(
                    color = LemonadeTheme.colors.background.bgElevated,
                    shape = RoundedCornerShape(LemonadeTheme.radius.radius300),
                ).clickable(onClick = onClick)
                .padding(LemonadeTheme.spaces.spacing200),
        horizontalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing300),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = album.artworkUrl,
            contentDescription = "Album artwork for ${album.name}",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(LemonadeTheme.sizes.size1400)
                    .clip(RoundedCornerShape(LemonadeTheme.radius.radius200)),
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing50),
        ) {
            LemonadeUi.Text(
                text = album.name,
                textStyle = LemonadeTheme.typography.bodyMediumMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            LemonadeUi.Text(
                text = album.artistName,
                textStyle = LemonadeTheme.typography.bodySmallRegular,
                color = LemonadeTheme.colors.content.contentSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
