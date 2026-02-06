package dev.srsouza.kmp.songlyst.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal expect fun createPlatformHttpClient(): HttpClient

public fun createHttpClient(): HttpClient =
    createPlatformHttpClient().config {
        install(ContentNegotiation) {
            // iTunes API returns text/javascript
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                },
                contentType = ContentType.Text.JavaScript,
            )
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                },
                contentType = ContentType.Application.Json,
            )
        }
        install(Logging) {
            logger =
                object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
            level = LogLevel.ALL
        }
    }
