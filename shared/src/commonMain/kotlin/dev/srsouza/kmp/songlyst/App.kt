package dev.srsouza.kmp.songlyst

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teya.lemonade.LemonadeTheme
import dev.srsouza.kmp.songlyst.navigation.SonglystNavHost

@Composable
public fun SonglystApp() {
    LemonadeTheme {
        SonglystNavHost(
            modifier =
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
        )
    }
}
