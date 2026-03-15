package com.jucode.nutrisport

actual val currentConfig: AppConfig = AppConfig(
    baseUrl = BuildConfig.BASE_URL,
    port = BuildConfig.PORT,
    sslPinningKey = BuildConfig.SSL_PINNING_KEY
)
