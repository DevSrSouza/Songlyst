package dev.srsouza.kmp.songlyst.di

import dev.srsouza.kmp.songlyst.navigation.NavigationStack
import dev.srsouza.kmp.songlyst.navigation.Navigator
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
public interface NavigatorProviders {
    @Provides
    public fun navigator(stack: NavigationStack): Navigator = stack
}
