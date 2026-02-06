package dev.srsouza.kmp.songlyst.network

import dev.srsouza.kmp.songlyst.di.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient

@ContributesTo(AppScope::class)
public interface NetworkProviders {
    public companion object {
        @Provides
        @SingleIn(AppScope::class)
        public fun provideHttpClient(): HttpClient = createHttpClient()
    }
}
