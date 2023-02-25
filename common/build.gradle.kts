plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("org.jetbrains.compose")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "common"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                implementation("org.jetbrains.compose.components:components-resources:1.4.0-alpha01-dev942")
                implementation("ca.gosyer:accompanist-pager:0.25.2")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

android {
    namespace = "com.qamar.composemultiplatform"
    compileSdk = 33
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDir("src/commonMain/resources")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}