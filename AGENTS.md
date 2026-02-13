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

## Business logic and data layer

Act as a Senior Kotlin Multiplatform Architect. Your task is to implement a feature module using KMP following the strictest standards of Clean Architecture, MVVM, and MVI using Kotlin Coroutines and Flow.

Core Requirements
Clean Architecture Layers:

Domain Layer: Pure Kotlin. Contains Entities, Repository Interfaces, and Use Cases. Use Cases must execute business logic and return a Flow<Result<T>>.
Data Layer: Repository implementations. Use Ktor for networking. Must include DTOs and Mappers to transform data into Domain Entities.
Presentation Layer (MVI + MVVM):
State: A single immutable UiState data class.
Intent: A sealed class representing user actions.
Effect: A sealed class for side effects (e.g., navigation, snackbars) using Channel or SharedFlow.
ViewModel: Expose a StateFlow for the UI. Process Intents using Coroutine Scopes and update the State accordingly.

Technical Implementation:
Asynchronous Logic: Use Flow operators (map, catch, onStart, flatMapLatest) for reactive data streams.
Threading: Ensure proper use of Dispatchers.Main and Dispatchers.Default/IO within the KMP lifecycle.
Error Handling: Implement a robust, centralized error-handling strategy using a Result wrapper.

Strict Standards:
No framework dependencies in the Domain layer.
Dependency Injection using Koin.
Fully documented code with clear separation of concerns.

The Task
Implement the implementation for: [INSERT FEATURE DESCRIPTION HERE].
Provide code for:
#Domain: Entity, Repository Interface, and Flow-based Use Case.
#Data: Ktor API Service, DTOs, and Repository Implementation.
#Presentation: MVI State/Intent/Effect contracts.
#ViewModel: Implementation using viewModelScope and StateFlow.

## Testing Instructions
*   Run all tests using `./gradlew test` before finalizing any changes.