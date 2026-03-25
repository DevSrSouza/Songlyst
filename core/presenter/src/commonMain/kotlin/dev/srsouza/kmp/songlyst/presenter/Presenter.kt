package dev.srsouza.kmp.songlyst.presenter

import androidx.compose.runtime.Composable

public interface Presenter<S : Any> {
    @Composable
    public fun present(): S
}
