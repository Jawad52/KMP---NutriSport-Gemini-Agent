# My Android Project AGENTS.md

## General Guidance

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