package dev.srsouza.kmp.songlyst.errorhandling

import kotlin.coroutines.cancellation.CancellationException

public inline fun <R> safeRunCatching(block: () -> R): Result<R> =
    try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
