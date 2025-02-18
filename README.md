**CharactersApp**
This is an Android application designed to showcase best practices for Android development as recommended by Google, 
built using modern technologies and patterns. The app leverages Kotlin, Jetpack Compose, and other cutting-edge Android 
tools to ensure high maintainability, testability, and scalability.

**Key Technologies Used**
Kotlin
Coroutines / Flow
Jetpack Compose
MVVM Architecture (Unidirectional Data Flow - Presentation layer)
Repository Pattern (Single source of truth)
Hilt (Dependency Injection)
Room (Local Database for Offline Support)
Clean Code Architecture

**Architecture Overview**
The application follows a clean architecture approach, with the code divided into three main parts:

**Data Layer:** Responsible for managing the data sources (network, local storage).
**Domain Layer:** Handles the business logic of the application.
**Presentation Layer:** Manages the UI (User Interface) and interacts with the ViewModel to present data to the user.
Presentation layer also support UDF, viewmodel [event-state] and composables interact with them to acknowledge event and update the state.

Additionally, there is a **core package** that manages common functionality and utilities required across modules, 
making the app modular and reusable.

**Features & Best Practices**
The app implements several best practices and architecture patterns recommended by Google to ensure high-quality Android development.

**Key Features Implemented:**
**Kotlin:** The app is built with Kotlin, making it concise, expressive, and less error-prone.
**Coroutines/Flow:** Asynchronous programming is handled using Coroutines and Flow, ensuring smooth background tasks with minimal threading issues.
**Jetpack Compose:** The UI is built using Jetpack Compose, a modern and powerful toolkit for building native UIs on Android.
**MVVM Architecture (Unidirectional Flow)**: The app uses Model-View-ViewModel (MVVM) architecture for clear separation of concerns. The presentation layer follows unidirectional data flow, making it easier to debug and test.
**Repository Pattern:** Data is abstracted through a Repository, which is responsible for providing data to the ViewModel. It pulls from both network and local sources (using Room for offline support).
**Hilt:** Hilt is used for dependency injection, making it easier to manage dependencies throughout the app.
**Room Database:** The app uses Room for persistent storage, enabling offline support and caching data for better user experience.
**Clean Code Architecture:** The code is organized to promote modularity, readability, and maintainability, with clear boundaries between layers.


**Updating Dependencies:** All Gradle dependencies, Kotlin version, and Jetpack libraries are kept up to date to leverage the latest features and ensure compatibility.
**Offline Support:** The app is capable of displaying data both online and offline. The data is cached locally using Room for offline access.
**Configuration Change Handling:** The app is designed to handle configuration changes (e.g., device rotation) without reloading data unnecessarily.
**Modularization:** The project is modularized to separate concerns and make future enhancements easier.


**Project Setup
Clone the Repository:**
git clone https://github.com/PS5020156/CharatersApp.git
cd CharactersApp
Open the Project in Android Studio:

Open Android Studio and select Open.
Navigate to the project directory and select it.
Sync Gradle:

Once the project is open, Android Studio will prompt you to sync Gradle. Click on Sync Now to sync the project with Gradle.
Build and Run:

Select a device or emulator from Android Studio’s device manager.
Click the Run button to build and run the application.


**Application Flow**
**Fetches data from an endpoint:** The app uses Retrofit for network calls to fetch data from a remote server.
**Displays data in a list:** The data is displayed in a LazyColumn using Jetpack Compose.
**Supports offline mode:** Data is cached using Room and displayed when offline.
**Handles configuration changes:** The app retains the data and UI state during configuration changes (device rotation).
**Shows an empty state:** The app displays a placeholder when there is no data.
**Navigates to list item details:** When a list item is clicked, the app navigates to a details screen using Jetpack Compose Navigation.

Dashboard screen and detail screen has same retrofit response & same ViewModel has been used to manage the state.