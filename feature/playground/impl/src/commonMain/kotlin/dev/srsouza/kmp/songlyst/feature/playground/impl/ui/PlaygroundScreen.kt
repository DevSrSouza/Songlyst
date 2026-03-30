package dev.srsouza.kmp.songlyst.feature.playground.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.teya.lemonade.IconButton
import com.teya.lemonade.LemonadeTheme
import com.teya.lemonade.LemonadeUi
import com.teya.lemonade.Text
import com.teya.lemonade.core.LemonadeIconButtonSize
import com.teya.lemonade.core.LemonadeIconButtonVariant
import com.teya.lemonade.core.LemonadeIcons
import dev.srsouza.kmp.songlyst.feature.playground.impl.presenter.PlaygroundEvent
import dev.srsouza.kmp.songlyst.feature.playground.impl.presenter.PlaygroundUiState

@Composable
internal fun PlaygroundScreen(
    state: PlaygroundUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(LemonadeTheme.colors.background.bgDefault),
    ) {
        PlaygroundToolbar(
            onBackClick = { state.eventSink(PlaygroundEvent.OnBackClicked) },
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = LemonadeTheme.spaces.spacing400),
            verticalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing400),
        ) {
            OutlinedTextField(
                value = state.field1,
                onValueChange = { state.eventSink(PlaygroundEvent.OnField1Changed(it)) },
                label = { LemonadeUi.Text(text = "Field 1") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = state.field2,
                onValueChange = { state.eventSink(PlaygroundEvent.OnField2Changed(it)) },
                label = { LemonadeUi.Text(text = "Field 2") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = state.field3,
                onValueChange = { state.eventSink(PlaygroundEvent.OnField3Changed(it)) },
                label = { LemonadeUi.Text(text = "Field 3") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun PlaygroundToolbar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LemonadeTheme.spaces.spacing400,
                    vertical = LemonadeTheme.spaces.spacing300,
                ),
        horizontalArrangement = Arrangement.spacedBy(LemonadeTheme.spaces.spacing200),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LemonadeUi.IconButton(
            icon = LemonadeIcons.ChevronLeft,
            contentDescription = "Go back",
            onClick = onBackClick,
            variant = LemonadeIconButtonVariant.Subtle,
            size = LemonadeIconButtonSize.Medium,
        )

        LemonadeUi.Text(
            text = "Playground",
            textStyle = LemonadeTheme.typography.headingMedium,
        )
    }
}
