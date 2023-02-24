package com.qamar.composemultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform