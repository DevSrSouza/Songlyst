package dev.srsouza.kmp.songlyst.feature.albumdetail.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.teya.lemonade.IconButton
import com.teya.lemonade.LemonadeTheme
import com.teya.lemonade.LemonadeUi
import com.teya.lemonade.Spinner
import com.teya.lemonade.Text
import com.teya.lemonade.core.LemonadeIconButtonSize
import com.teya.lemonade.core.LemonadeIconButtonVariant
import com.teya.lemonade.core.LemonadeIcons
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailEvent
import dev.srsouza.kmp.songlyst.feature.albumdetail.impl.presenter.AlbumDetailUiState
import dev.srsouza.kmp.songlyst.itunes.api.model.Album

@Composable
internal fun AlbumDetailScreen(
    state: AlbumDetailUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(LemonadeTheme.colors.background.bgDefault),
    ) {
        when (val content = state.content) {
            is AlbumDetailUiState.ContentState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    LemonadeUi.Spinner()
                }
            }

            is AlbumDetailUiState.ContentState.Success -> {
                AlbumDetailContent(
                    album = content.album,
                    onBackClick = { state.eventSink(AlbumDetailEvent.OnBackClicked) },
                )
            }

            is AlbumDetailUiState.ContentState.Error -> {
                ErrorContent(
                    message = content.message,
                    onBackClick = { state.eventSink(AlbumDetailEvent.OnBackClicked) },
                )
            }
        }
    }
}

@Composable
private fun AlbumDetailContent(
    album: Album,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
            ) {
                AsyncImage(
                    model = album.artworkUrl,
                    contentDescription = "Album artwork for ${album.name}",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxSize(),
                )
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LemonadeTheme.spaces.spacing400)
                        .padding(bottom = LemonadeTheme.spaces.spacing600),
                verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing300),
            ) {
                LemonadeUi.Text(
                    text = album.name,
                    textStyle = LemonadeTheme.typography.headingMedium,
                )

                LemonadeUi.Text(
                    text = album.artistName,
                    textStyle = LemonadeTheme.typography.bodyLargeMedium,
                    color = LemonadeTheme.colors.content.contentSecondary,
                )

                Column(
                    modifier = Modifier.padding(top = LemonadeTheme.spaces.spacing400),
                    verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing200),
                ) {
                    InfoRow(
                        label = "Genre",
                        value = album.genre,
                    )

                    InfoRow(
                        label = "Release Date",
                        value = album.releaseDate,
                    )
                }
            }
        }

        Box(
            modifier =
                Modifier
                    .padding(LemonadeTheme.spaces.spacing400),
        ) {
            Box(
                modifier =
                    Modifier
                        .clip(CircleShape)
                        .background(
                            LemonadeTheme.colors.background.bgDefault
                                .copy(alpha = 0.8f),
                        ),
            ) {
                LemonadeUi.IconButton(
                    icon = LemonadeIcons.ChevronLeft,
                    contentDescription = "Go back",
                    onClick = onBackClick,
                    variant = LemonadeIconButtonVariant.Subtle,
                    size = LemonadeIconButtonSize.Medium,
                )
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing50),
    ) {
        LemonadeUi.Text(
            text = label,
            textStyle = LemonadeTheme.typography.bodySmallRegular,
            color = LemonadeTheme.colors.content.contentTertiary,
        )

        LemonadeUi.Text(
            text = value,
            textStyle = LemonadeTheme.typography.bodyMediumMedium,
        )
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(LemonadeTheme.spaces.spacing600),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LemonadeUi.Text(
                text = message,
                textStyle = LemonadeTheme.typography.bodyMediumRegular,
                color = LemonadeTheme.colors.content.contentSecondary,
            )
        }

        Box(
            modifier =
                Modifier
                    .padding(LemonadeTheme.spaces.spacing400),
        ) {
            LemonadeUi.IconButton(
                icon = LemonadeIcons.ChevronLeft,
                contentDescription = "Go back",
                onClick = onBackClick,
                variant = LemonadeIconButtonVariant.Subtle,
                size = LemonadeIconButtonSize.Medium,
            )
        }
    }
}
