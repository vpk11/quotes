# QUOTES - Android App

A simple Android application that displays inspirational quotes. Users can view quotes and copy them to the clipboard.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots) (Optional)
- [Tech Stack & Libraries](#tech-stack--libraries)
- [Setup Instructions](#setup-instructions)
  - [Prerequisites](#prerequisites)
  - [Cloning the Repository](#cloning-the-repository)
  - [Importing into Android Studio](#importing-into-android-studio)
- [Building the APK](#building-the-apk)
  - [Debug APK](#debug-apk)
  - [Release APK](#release-apk)
- [Project Structure](#project-structure) (Brief Overview)
## Features

*   Displays a list of inspirational quotes.
*   Allows users to copy a quote to the clipboard by tapping on it.
*   "About" screen with app information, developer details, and links to portfolio, LinkedIn, and GitHub.
*   Loads quotes from a local JSON asset file.
*   Modern UI built with Jetpack Compose.

## Tech Stack & Libraries

*   **Language:** [Kotlin](https://kotlinlang.org/) (100%)
*   **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android's modern toolkit for building native UI.
*   **Architecture:** (Assumed MVVM-like with state hoisted to Composable functions, or simple Activity-driven UI if no explicit ViewModel is used for core quote display logic)
*   **Core Jetpack Libraries:**
    *   `androidx.activity:activity-compose` - For Compose integration in Activities.
    *   `androidx.compose.material3:material3` - Material Design 3 components.
    *   `androidx.compose.ui:ui-tooling-preview` - For Composable previews in Android Studio.
    *   `androidx.compose.runtime:runtime` - Core Compose runtime.
    *   `androidx.lifecycle:lifecycle-runtime-compose` - For observing Lifecycle-aware state.
*   **Serialization:**
    *   `org.jetbrains.kotlinx:kotlinx-serialization-json` - For parsing quotes from the local JSON file.
*   **Navigation:** (Assumed basic Intent-based navigation between Activities, or Jetpack Navigation Compose if implemented)
*   **Build Tool:** Gradle

## Setup Instructions

### Prerequisites

*   [Android Studio](https://developer.android.com/studio) (Latest stable version recommended)
*   Android SDK installed (ensure you have the necessary SDK Platforms and Build Tools).
*   Kotlin plugin installed in Android Studio (usually comes bundled).
*   An Android device or Emulator (API level 24+ recommended).

### Cloning the Repository
```sh
git clone https://github.com/vpk11/quotes.git
cd quotes
```
### Importing into Android Studio

1.  Open Android Studio.
2.  Select **File > New > Import Project...** (or **Open an existing Android Studio project** from the welcome screen).
3.  Navigate to the directory where you cloned or extracted the project (`quotes` folder).
4.  Select the root `build.gradle` file or the project directory itself.
5.  Click **OK**.
6.  Android Studio will then sync the project with Gradle. This might take a few minutes, especially on the first import, as it downloads necessary dependencies.

## Building the APK

### Debug APK

A debug APK is useful for testing and development.

1.  In Android Studio, select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
2.  Once the build is complete, a notification will appear in the bottom-right corner of Android Studio.
3.  Click on the **locate** link in the notification.
4.  This will open the `app/build/outputs/apk/debug/` directory, where you'll find `app-debug.apk`.
5.  You can install this APK directly onto an Android device or emulator.

Alternatively, running the app directly on a connected device or emulator from Android Studio (by clicking the "Run" button) will also build and install a debug version.

### Release APK

A release APK is a signed version of your app, ready for distribution (e.g., on the Google Play Store or other platforms).

**Important:** Before building a release APK, you need to set up signing configurations.

1.  **Generate an Upload Key and Keystore:**
    *   If you don't have one, follow the official Android documentation: [Sign your app](https://developer.android.com/studio/publish/app-signing#generate-key)
2.  **Configure Signing in `build.gradle`:**
    *   Open your module-level `build.gradle` (e.g., `app/build.gradle` or `app/build.gradle.kts`).
    *   Add a `signingConfigs` block and configure your release build type to use it.
    *   **Security Note:** It's highly recommended to store your keystore passwords securely (e.g., in `keystore.properties` and add that file to `.gitignore`, or use environment variables in a CI/CD system) rather than hardcoding them in `build.gradle`. See the "Secure your keys" section in the official docs.
3.  **Build the Release APK:**
    *   In Android Studio, select **Build > Generate Signed Bundle / APK...**.
    *   Choose **APK** and click **Next**.
    *   Select your keystore, enter passwords, and choose your key alias. Click **Next**.
    *   Choose the destination folder for the signed APK.
    *   Select the build variant (e.g., `release`).
    *   Click **Finish** (or **Create**).
    *   The signed APK will be generated in the specified destination folder (usually `app/release/app-release.apk`).

## Project Structure

A brief overview of key directories:

*   `app/src/main/java/com/vpk/quotes/`: Contains the main Kotlin source code.
    *   `MainActivity.kt`: The main entry point of the app, displays the list of quotes.
    *   `AboutActivity.kt`: Displays information about the app and developer.
    *   `Quote.kt`: Data class for representing a quote.
    *   `ui/theme/`: Contains Jetpack Compose theme files (`Color.kt`, `Theme.kt`, `Type.kt`).
    *   `utils/` (or similar): For utility functions like `loadQuotesFromAssets`.
*   `app/src/main/res/`: Contains application resources.
    *   `drawable/`: For image assets (icons, etc.).
    *   `mipmap/`: For launcher icons.
    *   `values/`: For XML resources like strings (`strings.xml`), colors (`colors.xml`), themes (`themes.xml`).
*   `app/src/main/assets/`: Contains asset files like `inspirational_quotes.json`.
*   `app/build.gradle` (or `app/build.gradle.kts`): Module-level Gradle build script.
*   `build.gradle` (or `build.gradle.kts`): Project-level Gradle build script.
*   `app/src/androidTest/`: For Android Instrumented tests.
*   `app/src/test/`: For local unit tests.
