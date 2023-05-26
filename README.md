
![Logo](https://user-images.githubusercontent.com/30949634/221370655-d88d6aa4-73b8-448c-8834-79a34cabdec8.svg)


# Compose Multiplatform 

Compose 

https://user-images.githubusercontent.com/30949634/236697673-dbf52629-9809-41a9-b9fd-8363ea527b99.mov

Multiplatform simplifies and accelerates UI and share it between Android, IOS, Desktop and Web.


<br/>

# Repositroy 

This repository will contain instructions to start your first Compose Multiplatform Project. 




## Plugin

Install 
[KMM](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
 plugin in Android Studio 

 ## Instructions
 - Create a new KMM project using the plugin above
 
 <img width="995" alt="Screenshot 2023-02-25 at 6 58 46 PM" src=
"https://user-images.githubusercontent.com/30949634/221369743-8512eb59-5c5e-4aee-8acb-575856b14ad0.png">
<img width="1440" alt="Screenshot 2023-05-08 at 9 17 35 PM" src="https://user-images.githubusercontent.com/30949634/236900376-f1fc7f3b-8ed3-46f5-a30e-58c5f1ec28ba.png">

 ### Setup Compose for KMM 
 
 in your `settings.gradle` file add the following dependency
 
```kotlin
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") // this one
    }
     plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
    }
}
```
in your `gradle.properteis` add compose plugin
```kotlin
#Versions
kotlin.version=1.8.20
agp.version=7.4.2
compose.version=1.4.0
```

in your `build.gradle.kts` add compose plugin
```kotlin
plugins {
    //trick: for the same plugin versions in all sub-modules
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
}



```

now inside `shared module` `build.gradle.kts` add these two plugins 
```kotlin
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods") // this 
    id("org.jetbrains.compose") // and this
}

```

in your `kotlin` block add the following lines 
```kotlin
  iosX64()
  iosArm64()
  iosSimulatorArm64()
  cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']" // this is for images 
    }
    
    // in android block add these lines to support common resources
    
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    
```

now BEFORE you sync you need to make sure about the following: 

- Install cocoapods  
```termial
brew install cocoapods

```
- cd to iosApp and init pod 
```terminal
 pod init
```
- install your pods
```terminal
pod install
```

In `sourceSets`

```kotlin
    sourceSets {
        val commonMain by getting {
           dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
       val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
```

and to avoid Compose targets '[uikit]' are experimental and may have bugs! Error add this in `gradle.properties`
```groovy
org.jetbrains.compose.experimental.uikit.enabled=true

```

Sync now !

## Let the magic begin 

Inside your `shared module` commonMain directory create your first Composable function to use it for Android and IOS

```kotlin
@Composable
internal fun App(){
    Text(Greeting().greet())
}
```

In your `AndroidMain` make `main.android` class to use your App function 
```kotlin
@Composable
fun Application(){
    App()
}
```


In your `IosMain` make `main.ios` class to use your App function 
```kotlin
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App() }

```

Now go to `AndoroidApp` module to use the function we made in main.android class
```kotlin
     Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Application()
                }
```

Next go to `IosApp`  module to use the function we made in main.ios class


```swift
import shared // this important to import don't forget it! 

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
     }
  }
  
 // inside your ContentView class  
 
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}

```

## Run 

Add this to solve dependencies conflicts in `gradle.properties` 
```groovy
kotlin.native.cacheKind=none
```

now you can run your android normally, for `IOS` you need to run the following command first 

```terminal
 ./gradlew build
 ./gradlew :shared:linkPodDebugFrameworkIosSimulatorArm64

```

Now open `iosApp.xcworkspace` inside xcode and run the app! or Run it inside Android Studio itself



## Resources 

[JetBrains Compose](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Getting_Started)

[Compose Mutliplatofrom Examples](https://github.com/JetBrains/compose-jb/tree/master/experimental/examples)

[Compose Mutliplatofrom Template](https://github.com/JetBrains/compose-multiplatform-ios-android-template)



<br/>
<br/>

Happy Kotlin 🚀!


