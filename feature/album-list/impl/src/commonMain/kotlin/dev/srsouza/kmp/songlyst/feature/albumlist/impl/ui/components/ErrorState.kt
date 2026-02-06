package dev.srsouza.kmp.songlyst.feature.albumlist.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.teya.lemonade.Button
import com.teya.lemonade.ExperimentalLemonadeComponent
import com.teya.lemonade.LemonadeTheme
import com.teya.lemonade.LemonadeUi
import com.teya.lemonade.Text
import com.teya.lemonade.core.LemonadeButtonSize
import com.teya.lemonade.core.LemonadeButtonVariant

@OptIn(ExperimentalLemonadeComponent::class)
@Composable
internal fun ErrorState(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(LemonadeTheme.spaces.spacing600),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LemonadeUi.Text(
            text = message,
            textStyle = LemonadeTheme.typography.bodyMediumRegular,
            color = LemonadeTheme.colors.content.contentSecondary,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = LemonadeTheme.spaces.spacing400),
        )

        LemonadeUi.Button(
            label = "Try Again",
            onClick = onRetryClick,
            variant = LemonadeButtonVariant.Primary,
            size = LemonadeButtonSize.Medium,
        )
    }
}
