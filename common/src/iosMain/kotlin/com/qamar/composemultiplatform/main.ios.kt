package com.qamar.composemultiplatform


import androidx.compose.ui.window.Application
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    Application("Example Application") {
        App()
    }