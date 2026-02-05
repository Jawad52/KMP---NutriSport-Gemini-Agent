package com.jucode.nutrisport

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jucode.nutrisport.router.Screen

@Composable
fun BottomNavigationBar(backStack: SnapshotStateList<Any>) {
    // Determine current selection for the NavigationBar
    val currentScreen = backStack.lastOrNull() ?: Screen.Home
    val items = listOf(Screen.Home, Screen.Cart, Screen.Deal, Screen.Profile)
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { screen.icon?.let { Icon(it, contentDescription = screen.label) } },
                label = { screen.label?.let { Text(it) } },
                selected = false,
                onClick = {
                    if (currentScreen != screen) {
                        backStack.clear()
                        backStack.add(screen)
                    }
                }
            )
        }
    }
}