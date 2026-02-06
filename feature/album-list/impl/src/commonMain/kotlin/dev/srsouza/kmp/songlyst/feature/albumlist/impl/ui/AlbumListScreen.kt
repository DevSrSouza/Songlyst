package dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.teya.lemonade.ExperimentalLemonadeComponent
import com.teya.lemonade.IconButton
import com.teya.lemonade.LemonadeTheme
import com.teya.lemonade.LemonadeUi
import com.teya.lemonade.Text
import com.teya.lemonade.core.LemonadeIconButtonSize
import com.teya.lemonade.core.LemonadeIconButtonVariant
import com.teya.lemonade.core.LemonadeIcons
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListEvent
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.AlbumListUiState
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.presenter.ContentEvent
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components.AlbumList
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components.ErrorState
import dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components.LoadingState
import dev.srsouza.kmp.songlyst.itunes.api.model.Album

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AlbumListScreen(
    state: AlbumListUiState,
    modifier: Modifier = Modifier,
) {
    val content = state.content

    Scaffold { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(LemonadeTheme.colors.background.bgDefault)
                    .padding(paddingValues),
        ) {
            AlbumListToolbar()

            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = LemonadeTheme.spaces.spacing400),
            ) {
                when (content) {
                    is AlbumListUiState.ContentState.Loading -> {
                        LoadingState()
                    }

                    is AlbumListUiState.ContentState.Success -> {
                        AlbumContent(
                            albums = content.albums,
                            onAlbumClick = { albumId ->
                                content.eventSink(ContentEvent.OnAlbumClicked(albumId))
                            },
                        )
                    }

                    is AlbumListUiState.ContentState.Error -> {
                        ErrorState(
                            message = content.message,
                            onRetryClick = {
                                state.eventSink(AlbumListEvent.OnRetryClicked)
                            },
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLemonadeComponent::class)
@Composable
private fun AlbumListToolbar(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LemonadeTheme.spaces.spacing400,
                    vertical = LemonadeTheme.spaces.spacing300,
                ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LemonadeUi.Text(
            text = "Songlyst",
            textStyle = LemonadeTheme.typography.headingMedium,
        )

        LemonadeUi.IconButton(
            icon = LemonadeIcons.Trophy,
            contentDescription = "Top Charts",
            onClick = { },
            variant = LemonadeIconButtonVariant.Subtle,
            size = LemonadeIconButtonSize.Medium,
        )
    }
}

@Composable
private fun AlbumContent(
    albums: List<Album>,
    onAlbumClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlbumList(
        albums = albums,
        onAlbumClick = onAlbumClick,
        modifier = modifier,
    )
}
