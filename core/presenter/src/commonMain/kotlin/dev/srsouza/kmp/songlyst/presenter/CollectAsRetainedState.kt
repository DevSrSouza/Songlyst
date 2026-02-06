package dev.srsouza.kmp.songlyst.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
public fun <T> StateFlow<T>.collectAsRetainedState(context: CoroutineContext = EmptyCoroutineContext): State<T> =
    collectAsRetainedState(value, context)

@Composable
public fun <T : R, R> Flow<T>.collectAsRetainedState(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext,
): State<R> =
    produceRetainedState(initial, this, context) {
        if (context == EmptyCoroutineContext) {
            collect { value = it }
        } else {
            withContext(context) { collect { value = it } }
        }
    }
