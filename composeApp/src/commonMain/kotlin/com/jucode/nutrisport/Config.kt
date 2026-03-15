package com.jucode.nutrisport

data class AppConfig(
    val baseUrl: String,
    val port: Int,
    val sslPinningKey: String
)

expect val currentConfig: AppConfig
