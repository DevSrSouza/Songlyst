package dev.srsouza.kmp.songlyst.integration.test

import androidx.compose.runtime.Composable
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.srsouza.kmp.songlyst.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlin.reflect.KClass

internal class FakeNavigator(
    private val parentScope: CoroutineScope,
) : Navigator {
    interface RouteHandler {
        @Composable
        fun present(route: Route): Any
    }

    class BackStackEntry(
        val route: Route,
        val stateFlow: StateFlow<*>,
        private val entryScope: CoroutineScope,
    ) {
        fun destroy() {
            entryScope.cancel()
        }
    }

    private val handlers = mutableMapOf<KClass<out Route>, RouteHandler>()
    private val mutableBackStack = mutableListOf<BackStackEntry>()

    val backStack: List<BackStackEntry> get() = mutableBackStack.toList()
    val backStackSize: Int get() = mutableBackStack.size

    inline fun <reified R : Route> onRoute(noinline block: @Composable (route: R) -> Any) {
        handlers[R::class] =
            object : RouteHandler {
                @Composable
                override fun present(route: Route): Any {
                    @Suppress("UNCHECKED_CAST")
                    return block(route as R)
                }
            }
    }

    fun start(route: Route) {
        push(route)
    }

    override fun navigate(route: Route) {
        push(route)
    }

    override fun popBackStack() {
        mutableBackStack.removeAt(mutableBackStack.lastIndex).destroy()
    }

    private fun push(route: Route) {
        val handler =
            handlers[route::class]
                ?: error("No handler registered for ${route::class.simpleName}")

        val entryScope =
            CoroutineScope(
                parentScope.coroutineContext + Job(parentScope.coroutineContext[Job]),
            )

        val stateFlow =
            entryScope.launchMolecule(RecompositionMode.Immediate) {
                handler.present(route)
            }

        mutableBackStack.add(BackStackEntry(route, stateFlow, entryScope))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> currentState(): T = mutableBackStack.last().stateFlow.value as T

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> awaitState(predicate: (T) -> Boolean = { true }): T =
        (mutableBackStack.last().stateFlow as StateFlow<T>).first(predicate)
}
