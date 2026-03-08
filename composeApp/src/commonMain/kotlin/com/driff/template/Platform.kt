package com.driff.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform