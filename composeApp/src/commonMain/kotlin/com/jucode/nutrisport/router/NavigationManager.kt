package com.jucode.nutrisport.router

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationManager {
    private val _shortcutEvents = Channel<String>(Channel.BUFFERED)
    val shortcutEvents = _shortcutEvents.receiveAsFlow()

    fun triggerShortcut(id: String) {
        _shortcutEvents.trySend(id)
    }
}
