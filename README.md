# Customer Onboarding Application - Technical Assessment

## 📝 Overview
This is a two-screen Android application built to demonstrate clean architectural patterns, real-time form validation, and reactive state management using Jetpack compose Android development.

The application collects verified customer profiles, securely uploads data to a remote mock endpoint, handles network latency/errors gracefully, and presents an interactive data summary upon completion.

---

## 🛠️ Architecture & Core Decisions

The codebase strictly follows **Clean Architecture principles** wrapped inside an **MVVM (Model-View-ViewModel)** presentation pattern to achieve a total separation of concerns, scalability and testability.

[ Presentation (Compose/ViewModel) ] ──> [ Domain (Validator/Usecase) ] ──> [ Data (Repository/Retrofit) ]

1. **UI & Presentation Layer**: All screen inputs, error states, and loading states are packaged into a single immutable FormUiState data class. The UI simply observes this state via a read-only StateFlow.
Smooth Validation: Validation logic runs reactively inside the ViewModel instead of blocking the main thread on every single keystroke. This keeps the layout scrolling buttery smooth.

2. **Domain Layer**:This layer is pure Kotlin. Contains FormValidator and CreateCustomerUseCase. It doesn't know anything about Android frameworks, context, or third-party libraries, making the validation rules 100% unit-testable.

3. **Data Layer**: The network implementation wraps API calls in withContext(Dispatchers.IO). This guarantees that heavy network traffic or parsing runs completely in the background, making it safe to call from the Main thread without causing ANR freezes.



## 🌐 Mock API Setup

The application connects to a MockAPI.io

* **Mock Endpoint URL:** `https://6a1270a678d0434e0d5d3578.mockapi.io/api/v1/` 
* **HTTP Method:** `POST`
* **Content-Type:** `application/json` 
* **payload**: {
  "name": "bindiya tandel",
  "email": "bindiyatandel@gmail.com",
  "phone": "8155805356",
  "city": "Dubai"
  }



## 🌐 Setup & Build Instructions

**Prerequisites**:Android Studio: Ladybug (2024.2.1) or newer recommended.

**JDK Version**: Java 17 configured in Android Studio Gradle settings.

**Android SDK**: Compile and Target SDK set to API 34.

**Execution Steps**
* Clone the Repository
* Bash: git clone [https://github.com/TandelBindiya/CustomerOnBoarding.git](https://github.com/TandelBindiya/CustomerOnBoarding.git)
* Open Project: Launch Android Studio, select Open, and choose the root directory folder of the cloned repository. 
* Gradle Sync: Allow Android Studio to automatically download necessary dependencies (Retrofit, Compose, Kotlinx Serialization). If a cache sync timeout occurs, select File -> Invalidate Caches... -> Invalidate and Restart. 
* Deploy Application: Connect an Android emulator or an active physical testing device (API 26+) and hit the Run button (Shift + F10).
