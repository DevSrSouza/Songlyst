package dev.srsouza.kmp.songlyst.navigation

public interface Navigator {
    public fun navigate(route: Route)

    public fun popBackStack()
}
