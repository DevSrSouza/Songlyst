package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.RetainedValuesStoreRegistry
import androidx.compose.runtime.retain.retainRetainedValuesStoreRegistry
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator

public class RetainNavEntryDecorator<T : Any>(
    registry: RetainedValuesStoreRegistry,
) : NavEntryDecorator<T>(
    onPop = { contentKey -> registry.clearChild(contentKey) },
    decorate = { entry ->
        registry.LocalRetainedValuesStoreProvider(entry.contentKey) {
            entry.Content()
        }
    },
)

@Composable
public fun <T : Any> rememberRetainNavEntryDecorator(): RetainNavEntryDecorator<T> {
    val registry = retainRetainedValuesStoreRegistry()
    return remember(registry) { RetainNavEntryDecorator(registry) }
}
