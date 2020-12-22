# Confession App
An Android application to share and comment on others confessions. Comes together with a custom Nodejs server which can be found [here](https://github.com/Nikola-Milovic/Confession-Server).
This is another for fun project, featuring clean and organized architecture, where each layer is responsible for itself and coupling of layers within the application is kept down to a minimum.


App showcase
-------------

![Showcase](images/showcase.gif "Showcase")

Main app screen
--------------


Error screen
--------------
![error screen](images/error.gif "Error")

Empty feed
--------------

![empty feed](images/emptyfeed.gif "EmptyFeed")

Localized to Serbian
--------------


Libraries Used
--------------

* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help design robust, testable, and
  maintainable apps.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Koin][100] - A pragmatic lightweight dependency injection framework for Kotlin developers.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Layout widgets using different algorithms.
  * [Lottie][99] - Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations and renders them natively on mobile
* Third party
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks

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
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[100]: https://github.com/InsertKoinIO/koin


## LottieAnimations used in the application

[Attention by Houssem Ismail](https://lottiefiles.com/32338-attention)

[Spaceship by Arman Rokni](https://lottiefiles.com/4011-spaceship-empty-searching)

[Construction in progress by Ki8.net](https://lottiefiles.com/26531-construction-in-process)


