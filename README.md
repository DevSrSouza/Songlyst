# Songlyst

A Kotlin Multiplatform application that displays the top 100 iTunes albums, built with Compose Multiplatform for Android and iOS.

<img width="734" height="810" alt="Image" src="https://github.com/user-attachments/assets/538a1028-a967-4072-8d6c-414e0b2fde9e" />

## Tech Stack

| Category | Technology                                                                  | Version |
|----------|-----------------------------------------------------------------------------|---------|
| Language | Kotlin (Multiplatform)                                                      | 2.3.20-Beta1 |
| UI | [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) | 1.10.0 |
| DI | [Metro](https://github.com/ZacSweers/metro)                                 | 0.10.2 |
| Networking | Ktor                                                                        | 3.4.0 |
| Image Loading | [Coil](https://github.com/coil-kt/coil)                                     | 3.3.0 |
| Design System | [Lemonade](https://github.com/saltpay/lemonade-design-system)               | 0.3.3 |
| Build | Gradle + AGP                                                                | 9.3.1 / 9.0.0 |
| Static Analysis | Detekt + Ktlint                                                             | 1.23.8 / 1.5.0 |

## Requirements

- **Android Studio Meerkat Feature Drop** (2025.2.3) or newer — required for AGP 9.0 support
- **Xcode 16+** for iOS builds
- **JDK 17**

## Running the Project

**Android:**

Open the project in Android Studio 2025.2.3+ and run the `androidApp` configuration, or from the terminal:

```bash
./gradlew :androidApp:installDebug
```

**iOS:**

```bash
open iosApp/iosApp.xcodeproj
```

Then build and run from Xcode on a simulator or device. The Kotlin framework is built automatically as part of the Xcode build process.

**Tests:**

```bash
# Run tests (requires XCode)
./gradlew allTests

# Run tests only for Android target but run at the host machine (AGP 9.0 testDebugUnitTest for kmp)
./gradlew testAndroidHostTest
```

## Architecture

### Module Structure

The project follows a multi-module architecture designed around two principles: **maximizing build parallelization** and **minimizing build invalidation**. 
Every module has a clear boundary so that changes in one feature don't trigger recompilation of unrelated modules.

```
androidApp/                      Android entry point
iosApp/                          iOS entry point (Xcode)
shared/                          KMP shared module (composes the app)

core/
  di/                            AppScope marker
  navigation/                    Route, Navigator, ScreenFactory
  presenter/                     Retained state utilities
  design/                        Design system re-exports (Lemonade) + Shimmer effect
  network/                       Ktor HttpClient + error mapping
  error-handling/                NetworkError, safeRunCatching
  testing/                       Shared test utilities

foundation/
  itunes/
    api/                         ITunesRepository interface + Album model
    impl/                        Real implementation (Ktor + caching)
    fake/                        Fake implementation for tests

feature/
  album-list/
    api/                         AlbumListRoute
    impl/                        Screen, Presenter, ScreenFactory
  album-detail/
    api/                         AlbumDetailRoute
    impl/                        Screen, Presenter, ScreenFactory

integration-test/                Cross-feature integration tests

build-logic/                     Gradle convention plugins
```

### Why Kotlin 2.3.20-Beta1?

Metro's aggregation feature (which enables cross-module `@ContributesBinding` and `@ContributesIntoSet` on Kotlin/Native targets) requires a Kotlin version that Metro 0.10.2 explicitly supports. As of this writing, 2.3.20-Beta1 is the latest version compatible with Metro's native aggregation. Without this, the contributor pattern would only work on the JVM target.

## Testing

The project includes integration tests that verify cross-feature behavior using fake implementations:

- **`FakeITunesRepository`** — allows controlled emission of data and errors for deterministic test scenarios
- **`FakeNavigator`** — a test navigator that drives presenters via Molecule, allowing assertion on UI state without a running UI
- **Integration tests** cover: list-to-detail navigation, back navigation with state preservation, error states, error recovery, and invalid route handling

Tests use Kotest for assertions, Turbine for Flow testing, and Molecule for composable presenter testing.

## Good to be mention

- `retain`: Projects uses the new official Compose runtime retain api instead of ViewModel to survive the presenter instance during Navigation and Configuration changes.
- UI Architecture follows a [Circuit](https://github.com/slackhq/circuit) like pattern with states data class containing eventSink (See also [Molecule](https://github.com/cashapp/molecule)).
- The project uses a in memory cache for simplicity that in the future could be migrated to SQL (Room or SQLDelight) or DataStore
