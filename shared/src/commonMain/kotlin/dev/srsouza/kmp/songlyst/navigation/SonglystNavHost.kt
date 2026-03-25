package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
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
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberRetainNavEntryDecorator(),
        ),
        entryProvider = { key ->
            val factory =
                factoriesByRoute[key::class]
                    ?: error("No ScreenFactory registered for ${key::class.simpleName}")
            navEntry(factory, key)
        },
    )
}

@Suppress("UNCHECKED_CAST")
private fun navEntry(
    factory: ScreenFactory<*, *>,
    key: Route,
): NavEntry<Route> =
    NavEntry(key) {
        val typedFactory = factory as ScreenFactory<Route, Any>
        val presenter = retain { typedFactory.createPresenter(key) }
        val state = presenter.present()
        typedFactory.Content(state)
    }
