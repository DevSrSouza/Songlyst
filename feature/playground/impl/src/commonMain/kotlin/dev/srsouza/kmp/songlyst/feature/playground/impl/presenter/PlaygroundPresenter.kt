package dev.srsouza.kmp.songlyst.feature.playground.impl.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.srsouza.kmp.songlyst.presenter.Presenter
import dev.zacsweers.metro.Inject

public class PlaygroundPresenter
    @Inject
    constructor(
        private val navigator: Navigator,
    ) : Presenter<PlaygroundUiState>() {
        @Composable
        override fun present(): PlaygroundUiState {
            var field1 by remember { mutableStateOf("") }
            var field2 by remember { mutableStateOf("") }
            var field3 by remember { mutableStateOf("") }

            val eventSink: (PlaygroundEvent) -> Unit = { event ->
                when (event) {
                    PlaygroundEvent.OnBackClicked -> navigator.popBackStack()
                    is PlaygroundEvent.OnField1Changed -> field1 = event.value
                    is PlaygroundEvent.OnField2Changed -> field2 = event.value
                    is PlaygroundEvent.OnField3Changed -> field3 = event.value
                }
            }

            return PlaygroundUiState(
                field1 = field1,
                field2 = field2,
                field3 = field3,
                eventSink = eventSink,
            )
        }
    }
