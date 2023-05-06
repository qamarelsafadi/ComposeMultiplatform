allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    id("org.jetbrains.compose").version("1.4.0") apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
