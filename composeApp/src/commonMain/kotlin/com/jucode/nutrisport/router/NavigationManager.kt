package com.jucode.nutrisport.router

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {
    private val _shortcutEvents = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val shortcutEvents = _shortcutEvents.asSharedFlow()

    fun triggerShortcut(id: String) {
        _shortcutEvents.tryEmit(id)
    }
}
