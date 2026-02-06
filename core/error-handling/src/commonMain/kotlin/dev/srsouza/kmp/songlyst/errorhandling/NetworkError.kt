package dev.srsouza.kmp.songlyst.errorhandling

public sealed class NetworkError(
    message: String? = null,
) : Exception(message) {
    public class NoConnection : NetworkError()

    public class Timeout : NetworkError()

    public class ServerError : NetworkError()

    public data class Unknown(
        override val message: String?,
    ) : NetworkError(message)

    // TODO: Migrate this to use Resources
    public fun toUserMessage(): String =
        when (this) {
            is NoConnection -> "Unable to load albums. Check your internet connection."
            is Timeout -> "Request timed out."
            is ServerError -> "Server is temporarily unavailable."
            is Unknown -> "Something went wrong."
        }
}
