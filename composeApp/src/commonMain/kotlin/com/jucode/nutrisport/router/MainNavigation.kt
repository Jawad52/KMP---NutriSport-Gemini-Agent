package com.jucode.nutrisport.router

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.jucode.nutrisport.BottomNavigationBar
import com.jucode.nutrisport.dashboard.DashboardPage
import com.jucode.nutrisport.dashboard.ProductDetailsPage
import com.jucode.nutrisport.profile.ProfilePage

@Composable
fun MainNavigation() {
    val backStack = remember { mutableStateListOf<Any>(Screen.Home) }
    Scaffold(
        bottomBar = { BottomNavigationBar(backStack) }
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(paddingValues),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is Screen.Home -> NavEntry(key) {
                        DashboardPage(onProductClick = { product ->
                            backStack.add(Screen.ProductDetails(product.id))
                        })
                    }
                    is Screen.Cart -> NavEntry(key) { PlaceholderScreen("Cart") }
                    is Screen.Deal -> NavEntry(key) { PlaceholderScreen("Deals") }
                    is Screen.Profile -> NavEntry(key) { ProfilePage() }
                    is Screen.ProductDetails -> NavEntry(key) {
                        ProductDetailsPage(
                            productId = key.productId,
                            onBack = { backStack.removeLastOrNull() }
                        )
                    }
                    else -> NavEntry(Unit) { PlaceholderScreen("Unknown") }
                }
            }
        )
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(name, style = MaterialTheme.typography.headlineLarge)
    }
}
