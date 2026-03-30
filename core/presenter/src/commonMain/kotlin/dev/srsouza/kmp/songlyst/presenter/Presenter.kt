package dev.srsouza.kmp.songlyst.presenter

import androidx.compose.runtime.Composable
import app.cash.molecule.RecompositionMode.Immediate
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow

public abstract class Presenter<S : Any> {
    public val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Composable
    protected abstract fun present(): S

    public val viewState: StateFlow<S> by lazy(LazyThreadSafetyMode.NONE) {
        scope.launchMolecule(mode = Immediate) { present() }
    }

    /**
     * Launches this presenter's [present] function in the given [scope] using Molecule
     * with [Immediate] recomposition mode. Intended for testing, where the test controls
     * the coroutine scope lifetime (e.g. backgroundScope from runTest).
     */
    public fun testIn(scope: CoroutineScope): StateFlow<S> =
        scope.launchMolecule(mode = Immediate) { present() }

    public fun onDispose() {
        scope.cancel()
    }
}
