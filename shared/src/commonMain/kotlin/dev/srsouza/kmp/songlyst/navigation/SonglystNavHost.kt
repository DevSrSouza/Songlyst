package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.srsouza.kmp.songlyst.di.AppGraphHolder

@Composable
public fun SonglystNavHost(modifier: Modifier = Modifier) {
    val appGraph = retain { AppGraphHolder.getInstance() }
    val navigationStack = appGraph.navigationStack
    val factoriesByRoute =
        remember(appGraph) {
            appGraph.screenFactories.associateBy { it.route }
        }

    NavDisplay(
        backStack = navigationStack.backStack,
        onBack = { navigationStack.popBackStack() },
        modifier = modifier,
        entryProvider = { key ->
            val factory =
                factoriesByRoute[key::class]
                    ?: error("No ScreenFactory registered for ${key::class.simpleName}")
            navEntry(factory, key)
        },
    )
}

@Suppress("UNCHECKED_CAST")
private fun <T : Route> navEntry(
    factory: ScreenFactory<T>,
    key: Route,
): NavEntry<Route> =
    NavEntry(key) {
        factory.Content(key as T)
    }
