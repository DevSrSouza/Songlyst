package dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.teya.lemonade.LemonadeTheme
import dev.srsouza.kmp.songlyst.design.shimmerEffect

@Composable
internal fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        SkeletonList()
    }
}

@Composable
private fun SkeletonList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing200),
    ) {
        items(10) { index ->
            SkeletonListItem(index = index)
        }
    }
}

@Composable
private fun SkeletonListItem(
    index: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(LemonadeTheme.spaces.spacing200),
        horizontalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing300),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(LemonadeTheme.sizes.size1400)
                    .clip(RoundedCornerShape(LemonadeTheme.radius.radius200))
                    .shimmerEffect(),
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing100),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.7f)
                        .height(LemonadeTheme.sizes.size400)
                        .clip(RoundedCornerShape(LemonadeTheme.radius.radius100))
                        .shimmerEffect(),
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .height(LemonadeTheme.sizes.size300)
                        .clip(RoundedCornerShape(LemonadeTheme.radius.radius100))
                        .shimmerEffect(),
            )
        }
    }
}
