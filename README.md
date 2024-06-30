# Wheel Of Fortune - Compose Multiplatform

A Compose Multiplatform Application for conducting draws using the wheel of fortune.
The user enters data into the text fields or paste whole text via paste button, and then launch the wheel to randomly select one of the cells and displays the data from it.

## Description

This project is built using Compose Multiplatform, targeting Android, iOS, and Desktop platforms. The application leverages common ViewModels, utilizes Compose Jetpack Navigation, and implements Koin for dependency injection. Additionally, the app supports adaptive layouts.


Uploading readme-preview.mp4…


## Prerequisites

- [Android Studio](https://developer.android.com/studio)
- [Xcode](https://developer.apple.com/xcode/) (for iOS target)
- [JDK (Need to have separate JDK not built into Android Studio. Otherwise building desktop (msi, exe, dmg, etc) distribution will cause the error: "Failed to check JDK distribution: 'jpackage' is missing will appear")](https://www.oracle.com/java/technologies/downloads/) (for Desktop target)

## Build
1. **Build and run the project:**
  - **Android:**
    via Android Studio
  - **iOS:**
    Open `iosApp.xcworkspace` in Xcode and run.
  - **Desktop:**
    ```sh
    ./gradlew :composeApp:run 
    ```
2. **Build distributions:**
  - **Exe:**
    ```sh
    ./gradlew packageExe
    ```
  - **Msi:**
    ```sh
    ./gradlew packageExe
    ```
  - **Dmg:**
    ```sh
    ./gradlew packageDmg
    ```
## Dependencies

The project uses a few essential libraries:

- **Common ViewModels:**
  Shared view models for consistent logic across platforms.
- **Compose Jetpack Navigation:**
  For handling navigation within the app.
- **Koin:**
  Dependency injection for better code modularity.
- **Kotlinx Coroutines Swing:**
  Coroutines Swing implementation for desktop applications.
- **Material 3**
- **Compose Material Icons Extended**
## Adaptive Layouts

The app includes adaptive layouts that automatically adjust to different screen sizes, ensuring the best possible user experience whether on a mobile device or a desktop.
This contributes to a lot of duplicate code. Waiting multiplatform support for [NavigationSuiteScaffold](https://github.com/JetBrains/compose-multiplatform/issues/4952)

---


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
