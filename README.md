# PicSum-Photos
      The sample project is a small photo list and viewer app. It consists of two views. The first is a list
      of available images. The second is a detail view displaying a selected image and one piece of
      associated metadata. The data for this app is loaded from https://picsum.photos/. 
      The list view shows the list of images & the detail view shows the image & author information. If the 
      image is a landscape image it is shown centered vertically else it is shown at the top followed by the 
      author name below the image. 

# Branch Information:

      This project has two different branches. develop & v2-implementation. 

# develop:
      This branch uses the https://picsum.photos/list endpoint to load data. This endpoint provides no pagination support. 
      It has the json node "filename" in the response. So this branch shows the filename in the home screen. 
      Tapping on that item it takes to the details screen.

# v2-implementation:
      This branch is using https://picsum.photos/v2/list?page=1&limit=10 which provides support for pagination. But 
      the response from this url doesn't have any "filename" json node. So the home screen list displays the image along
      with the author information. It has a pagination support using jetpack paging library. 

## Setup and Configuration

      To build and run the multi-module Android app, follow these steps:
      
      1. Clone the repository: `git clone https://github.com/adey-babbel/PicSum-Photos.git`
      
      2. Open the project in Android Studio.

      3. Checkout to develop or v2-implementation branch.
      
      4. Build the project to download dependencies and compile the modules.
      
      5. Run the `app` module on the desired device or emulator.


# Multi-Module Android App With Clean Architecture.

    This is a sample multi-module Android application that demonstrates the benefits of modularization and follows 
    best practices for building scalable and maintainable apps. This multi-module android app is divided into server modules

    1. app : responsible for the presentation layer or UI components.
    2. domain: represents the core business logic.
    3. data: responsible for managing data & typically interacts with various data sources.

    Each module is have well-defined boundaries and communicate with other modules through interfaces. Avoids tight coupling between 
    modules to ensure modularity and easier maintenance.

    Each module have its own set of unit tests to ensure its functionality is working as expected. Uses test frameworks like 
    JUnit and Mockito to write comprehensive tests for your modules.

    Business logic is in the domain layer, adhering to principles like separation of concerns and single responsibility. This promotes
    maintainability and testability & follow clean architecture principles.

    To manage module dependencies, this project is using Gradle Kotlin DSL (build.gradle.kts) for declaring dependencies between modules. 
    Avoid circular dependencies and ensure clear module hierarchy.

## Technologies Used

The Android project incorporates the following technologies and frameworks:

- **Kotlin**: The project is developed using the Kotlin programming language, which is the recommended language for Android app development. Kotlin offers concise syntax, null safety, and full interoperability with existing Java code.

- **Android Jetpack**: Android Jetpack is a set of libraries and tools provided by Google to accelerate Android app development. The project utilizes various Jetpack components such as ViewModel, Flow, Navigation to implement modern Android architecture patterns and streamline development.

- **Declarative UI**: Jetpack Compose follows a declarative programming model, where you describe the desired UI state and Compose takes care of updating the UI accordingly. This simplifies UI development by reducing boilerplate code and providing a more intuitive way to build UIs.

- **Composable Functions**: UI components in Jetpack Compose are defined as composable functions. Composable functions are lightweight, reusable, and allow you to compose complex UIs from smaller, self-contained building blocks. These functions can accept parameters and emit UI elements based on their inputs.

- **Material Design**: Jetpack Compose seamlessly integrates with Material Design, offering a rich set of pre-built Material components. You can easily incorporate buttons, text fields, cards, lists, and more into your UI using Material Design guidelines and theming.

- **MVVM Architecture**: The project follows the Model-View-ViewModel (MVVM with clean architecture) architectural pattern, which separates the concerns of data, UI, and business logic. MVVM promotes a clean and maintainable codebase by leveraging data binding and lifecycle-aware components.

- **Retrofit**: Retrofit is used for network communication, making it easy to consume RESTful APIs. It offers a robust and flexible way to handle network requests and responses, and it integrates well with other Jetpack components.

- **Coil**: Coil is used for efficient image loading and caching.
  
- **Moshi**: Moshi is a JSON serialization/deserialization library for Java and Kotlin

- **Dagger Hilt**: Dagger Hilt is used for dependency injection. It provides a simple and concise way to manage dependencies and facilitates code modularity, testability, and maintainability.

- **JUnit and Espresso**: The project incorporates JUnit and Espresso for unit testing and UI testing, respectively. These testing frameworks help ensure the correctness and robustness of the app by writing automated tests for different components and scenarios.









    
