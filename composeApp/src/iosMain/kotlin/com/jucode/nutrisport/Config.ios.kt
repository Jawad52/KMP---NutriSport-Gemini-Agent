package com.jucode.nutrisport

import platform.Foundation.NSBundle

actual val currentConfig: AppConfig = AppConfig(
    baseUrl = NSBundle.mainBundle.objectForInfoDictionaryKey("BASE_URL") as? String ?: "",
    port = (NSBundle.mainBundle.objectForInfoDictionaryKey("PORT") as? String)?.toInt() ?: 443,
    sslPinningKey = NSBundle.mainBundle.objectForInfoDictionaryKey("SSL_PINNING_KEY") as? String ?: ""
)
