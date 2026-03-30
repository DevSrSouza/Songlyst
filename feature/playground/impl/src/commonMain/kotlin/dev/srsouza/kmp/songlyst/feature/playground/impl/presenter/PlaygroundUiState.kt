package dev.srsouza.kmp.songlyst.feature.playground.impl.presenter

public data class PlaygroundUiState(
    val field1: String,
    val field2: String,
    val field3: String,
    val eventSink: (PlaygroundEvent) -> Unit,
)

public sealed interface PlaygroundEvent {
    public data object OnBackClicked : PlaygroundEvent
    public data class OnField1Changed(val value: String) : PlaygroundEvent
    public data class OnField2Changed(val value: String) : PlaygroundEvent
    public data class OnField3Changed(val value: String) : PlaygroundEvent
}
