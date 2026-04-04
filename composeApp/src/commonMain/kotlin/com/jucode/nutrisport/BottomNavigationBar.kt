package com.jucode.nutrisport

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jucode.nutrisport.router.Screen

@Composable
fun BottomNavigationBar(backStack: SnapshotStateList<Any>) {
    // Determine current selection for the NavigationBar
    val currentScreen = backStack.lastOrNull() ?: Screen.Home
    val items = listOf(Screen.Home, Screen.Cart, Screen.Deal, Screen.Profile)
    
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .navigationBarsPadding(),
        shape = RoundedCornerShape(28.dp),
        tonalElevation = 4.dp,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            tonalElevation = 0.dp
        ) {
            items.forEach { screen ->
                val isSelected = currentScreen == screen
                NavigationBarItem(
                    icon = { screen.icon?.let { Icon(it, contentDescription = screen.label) } },
                    label = null,
                    alwaysShowLabel = false,
                    selected = isSelected,
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
}