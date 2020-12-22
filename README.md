# Confession App
An Android application to share and comment on others confessions. Comes together with a custom Nodejs server which can be found [here](https://github.com/Nikola-Milovic/Confession-Server).
This is another for fun project, featuring clean and organized architecture, where each layer is responsible for itself and coupling of layers within the application is kept down to a minimum.


# App showcase


![Showcase](images/showcase.gif "Showcase")

### Main app screen



### Error screen

![error screen](images/error.gif "Error")

### Empty feed


![empty feed](images/emptyfeed.gif "EmptyFeed")

### Localized to Serbian


# Architecture

Each feature, a logical unit, is placed within a module, meaning that all of the logic for a single screen (usually) is inside a single module. This way of architecturing our app gives us, at least these 3 benefits: 

* Clear isolation between the modules. The whole goal of the MVP pattern is to make sure your business logic and presentations layer are not tightly coupled together. By defining these in different modules it makes this separation more clear and further emphasises decoupling between non-related logic.
* Code reuse - allows us to have a common module, or a core module that our other modules can implement. The code inside that module can be reused throught the application and features. In our case, we have a Common module and Library test utils. Both featuring multiple useful files and util functions.
* Splitting your project into sub-project allows more efficient build and testing as you can rebuild and test only the modules that changed rather than recompiling the whole project whenever there's a change in one of the files. In a production environment it also allows programmers to work on different parts of the application without interfering with the work of others.

## Modules

The application is made in a mix of MVP, and MVI/MVVM for the presentation layer. Each Feature-Module consists of a DATA, DOMAIN, PRESENTATION and DI layers. 

We have three kinds of modules in the application:

* app module - this is the main module. It contains code that lays foundation for other modules, it starts Koin and loads the KoinModules used throughout the application, it also features NavHostFragment and NavManager.
* application-independent Common module containing common code base that could be reused throughout the application and other Modules. It holds util functions and other useful helper classes. And Library-test-utils that holds useful utils needed for easier testing.
* feature modules - the most common type of module containing all code related to a given feature. Basically a logic unit, where all of the logic for that given functionality is packed together in a single Module. Common examples could be LoginModule, HomepageModule...



# Libraries Used


* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help design robust, testable, and
  maintainable apps.
  * Clean Architecture (at module level)
  * MVVM + MVI (presentation layer)
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Koin][100] - A pragmatic lightweight dependency injection framework for Kotlin developers.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Material][32] - Material Components for Android is a drop-in replacement for Android's Design Support Library.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Layout widgets using different algorithms.
  * [RefreshLayout][93] - Layout with swipe to refresh functionality
  * [Lottie][99] - Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations and renders them natively on mobile
* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
  * BuildSrc
* Third party
  * [Kotlin Coroutines][91] - Managing background threads with simplified code and reducing needs for callbacks
  * [Timber][92] - Logger with a small, extensible API which provides utility on top of Android's normal Log class.
  * [Retrofit][94] - A type-safe HTTP client for Android and Java
  

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycl
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[32]: https://github.com/material-components/material-components-android
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://github.com/JakeWharton/timber
[93]: https://developer.android.com/training/swipe/add-swipe-interface
[94]: https://square.github.io/retrofit/
[99]: https://github.com/airbnb/lottie-android
[100]: https://github.com/InsertKoinIO/koin


### LottieAnimations used in the application

[Attention by Houssem Ismail](https://lottiefiles.com/32338-attention)

[Spaceship by Arman Rokni](https://lottiefiles.com/4011-spaceship-empty-searching)

[Construction in progress by Ki8.net](https://lottiefiles.com/26531-construction-in-process)


