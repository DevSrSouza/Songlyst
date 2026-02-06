package dev.srsouza.kmp.songlyst.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.CoroutineContext

@Composable
public fun <T> produceRetainedState(
    initialValue: T,
    producer: suspend ProduceStateScope<T>.() -> Unit,
): State<T> {
    val result = retain { mutableStateOf(initialValue) }
    LaunchedEffect(Unit) {
        ProduceRetainedStateScopeImpl(result, coroutineContext).producer()
    }
    return result
}

@Composable
public fun <T> produceRetainedState(
    initialValue: T,
    key1: Any?,
    producer: suspend ProduceStateScope<T>.() -> Unit,
): State<T> {
    val result = retain { mutableStateOf(initialValue) }
    LaunchedEffect(key1) {
        ProduceRetainedStateScopeImpl(result, coroutineContext).producer()
    }
    return result
}

@Composable
public fun <T> produceRetainedState(
    initialValue: T,
    key1: Any?,
    key2: Any?,
    producer: suspend ProduceStateScope<T>.() -> Unit,
): State<T> {
    val result = retain { mutableStateOf(initialValue) }
    LaunchedEffect(key1, key2) {
        ProduceRetainedStateScopeImpl(result, coroutineContext).producer()
    }
    return result
}

@Composable
public fun <T> produceRetainedState(
    initialValue: T,
    vararg keys: Any?,
    producer: suspend ProduceStateScope<T>.() -> Unit,
): State<T> {
    val result = retain { mutableStateOf(initialValue) }
    LaunchedEffect(keys = keys) {
        ProduceRetainedStateScopeImpl(result, coroutineContext).producer()
    }
    return result
}

private class ProduceRetainedStateScopeImpl<T>(
    state: MutableState<T>,
    override val coroutineContext: CoroutineContext,
) : ProduceStateScope<T>,
    MutableState<T> by state {
    override suspend fun awaitDispose(onDispose: () -> Unit): Nothing {
        try {
            suspendCancellableCoroutine<Nothing> { }
        } finally {
            onDispose()
        }
    }
}
