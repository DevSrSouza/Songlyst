package dev.srsouza.kmp.songlyst.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

internal actual fun createPlatformHttpClient(): HttpClient = HttpClient(Darwin)
