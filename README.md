
![Logo](https://user-images.githubusercontent.com/30949634/221370655-d88d6aa4-73b8-448c-8834-79a34cabdec8.svg)


# Compose Multiplatform 

Compose Multiplatform simplifies and accelerates UI and share it between Android, IOS, Desktop and Web.

https://user-images.githubusercontent.com/30949634/221372967-e14c1747-b7c9-48fe-9b49-cb3d88ad04e7.mp4

## Experimental targets
iOS is experimental and under development. Use it at your own risk and that explains why you will have some laggy animation with it.


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
}
```
in your `build.gradle.kts` add compose plugin
```kotlin
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0-alpha09").apply(false)
    id("com.android.library").version("8.0.0-alpha09").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    id("org.jetbrains.compose").version("1.3.0") apply false // this one
}

```

now inside `shared module` `build.gradle.kts` add these two plugins 
```kotlin
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods") // this 
    id("org.jetbrains.compose") // and this
}}

```

in your `kotlin` block add the following lines 
```kotlin
ios()
iosSimulatorArm64()

cocoapods {
    summary = "Some description for the Shared Module"
    homepage = "Link to the Shared Module homepage"
    version = "1.0"
    ios.deploymentTarget = "16.1"
    podfile = project.file("../iosApp/Podfile")
    framework {
        baseName = "shared"
        isStatic = true
    }
}

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
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
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
fun MainViewController(): UIViewController =
    Application("Example Application") {
        App()
    }
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

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        let mainViewController = Main_iosKt.MainViewController()
        window?.rootViewController = mainViewController
        window?.makeKeyAndVisible()
        return true
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

Now open `iosApp.xcworkspace` inside xcode and run the app!



## Resources 

[JetBrains Compose](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Getting_Started)



[Compose Mutliplatofrom Examples](https://github.com/JetBrains/compose-jb/tree/master/experimental/examples)




<br/>
<br/>

Happy Kotlin 🚀!


