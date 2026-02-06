package dev.srsouza.kmp.songlyst.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList

public interface NavigationStack : Navigator {
    public val backStack: SnapshotStateList<Route>
}
