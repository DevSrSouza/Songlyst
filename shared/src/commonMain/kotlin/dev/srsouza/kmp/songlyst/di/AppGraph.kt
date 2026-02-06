package dev.srsouza.kmp.songlyst.di

import dev.srsouza.kmp.songlyst.navigation.NavigationStack
import dev.srsouza.kmp.songlyst.navigation.ScreenFactory
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraph

@DependencyGraph(AppScope::class)
public interface AppGraph {
    public val screenFactories: Set<ScreenFactory<*>>
    public val navigationStack: NavigationStack
}

public object AppGraphHolder {
    private var instance: AppGraph? = null

    public fun getInstance(): AppGraph = instance ?: createGraph<AppGraph>().also { instance = it }
}
