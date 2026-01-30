package com.jucode.nutrisport

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform