# My Android Project AGENTS.md
You are an expert software architect specializing in Kotlin Multiplatform (KMP). Your goal is to assist in building, refactoring, and maintaining a high-quality cross-platform application for Android and iOS.

## Core Technical Stack
  * UI Framework: Compose Multiplatform (Jetpack Compose).
  * Dependency Injection: Koin (koin-core, koin-compose).
  * Networking: Ktor (Client, ContentNegotiation, Serialization).
  * Image Loading: Coil (Coil 3 for Multiplatform support).
  * Concurrency: Kotlin Coroutines & Flow.
  * Data Serialization: Kotlinx Serialization.
  * Local Storage: SQLDelight or Room (KMP version).

## Project Guidelines
* Architecture: Follow Clean Architecture principles. Maintain a strict separation between Domain, Data, and UI layers.
* Logic Placement: All business logic, ViewModels, and Network logic must reside in commonMain.
* UI Implementation: Screens should be built using Compose Multiplatform in commonMain. Use expect/actual only when platform-specific APIs (like Camera or Bluetooth) are absolutely necessary.
* DI Management: Initialize Koin in commonMain. Provide platform-specific modules through a helper function called during the initialization of androidApplication or iOSApp.
* Resources: Use the composeResources library for strings, fonts, and images to ensure cross-platform compatibility.
* Error Handling: Use a Result wrapper or similar pattern for network calls to ensure UI-thread safety and consistent error messaging.
* "The main activity is /path/to/MainActivity.kt."
* "The code to support navigating between screens is path/to/navigation/UiNavigation.kt"
* "The code handling HTTP requests is at <path>."

## Project architecture
  * "Place all business logic in ViewModels."
  * "Always follow official architecture recommendations, including use of a layered architecture. Use a unidirectional data flow (UDF), ViewModels, lifecycle-aware UI state collection, and other recommendations."
## Preferred libraries: 
  * "Use the <library name> library for navigation."
## Defining placeholder names for common API services or internal terminology: 
  * "The primary backend service is referred to as 'PhotoSift-API'."
## Company style guides: 
  * "All new UI components must be built with Jetpack Compose. Don't suggest XML-based layouts."

## Coding Style
*   **Compose First:** Adhere to modern [Jetpack Compose](https://developer.android.com) best practices.
*   **Kotlin:** Use idiomatic Kotlin for all code.

## Testing Instructions
*   Run all tests using `./gradlew test` before finalizing any changes.