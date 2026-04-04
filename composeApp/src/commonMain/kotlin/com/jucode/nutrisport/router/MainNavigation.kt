package com.jucode.nutrisport.router

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.jucode.nutrisport.BottomNavigationBar
import com.jucode.nutrisport.cart.CartPage
import com.jucode.nutrisport.dashboard.DashboardPage
import com.jucode.nutrisport.dashboard.ProductDetailsPage
import com.jucode.nutrisport.deals.DealsPage
import com.jucode.nutrisport.deals.PromoCodePage
import com.jucode.nutrisport.notificaiton.NotificationPage
import com.jucode.nutrisport.profile.ChatBotPage
import com.jucode.nutrisport.profile.ProfilePage
import com.jucode.nutrisport.search.SearchPage
import org.koin.compose.koinInject

@Composable
fun MainNavigation() {
    val backStack = remember { mutableStateListOf<Any>(Screen.Home) }
    val navigationManager = koinInject<NavigationManager>()

    LaunchedEffect(Unit) {
        navigationManager.shortcutEvents.collect { shortcutId ->
            when (shortcutId) {
                "deals" -> {
                    backStack.clear()
                    backStack.add(Screen.Deal)
                }
                "cart" -> {
                    backStack.clear()
                    backStack.add(Screen.Cart)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.fillMaxSize(),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is Screen.Home -> NavEntry(key) {
                        DashboardPage(
                            onProductClick = { product ->
                                backStack.add(Screen.ProductDetails(product.id))
                            },
                            onSearchClick = { backStack.add(Screen.Search) },
                            onNotificationClick = { backStack.add(Screen.Notification) }
                        )
                    }
                    is Screen.Cart -> NavEntry(key) { CartPage() }
                    is Screen.Deal -> NavEntry(key) { 
                        DealsPage(
                            onOfferClick = {
                                backStack.add(Screen.PromoCode)
                            }
                        ) 
                    }
                    is Screen.Profile -> NavEntry(key) { 
                        ProfilePage(onContactUsClick = {
                            backStack.add(Screen.ChatBot)
                        }) 
                    }
                    is Screen.ProductDetails -> NavEntry(key) {
                        ProductDetailsPage(
                            productId = key.productId,
                            onBack = { backStack.removeLastOrNull() }
                        )
                    }
                    is Screen.Search -> NavEntry(key) {
                        SearchPage(onBack = { backStack.removeLastOrNull() })
                    }
                    is Screen.Notification -> NavEntry(key) {
                        NotificationPage(onBack = { backStack.removeLastOrNull() })
                    }
                    is Screen.ChatBot -> NavEntry(key) {
                        ChatBotPage(onBack = { backStack.removeLastOrNull() })
                    }
                    is Screen.PromoCode -> NavEntry(key) {
                        PromoCodePage(onBack = { backStack.removeLastOrNull() })
                    }
                    else -> NavEntry(key) { PlaceholderScreen(key.toString()) }
                }
            }
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavigationBar(backStack)
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(name, style = MaterialTheme.typography.headlineLarge)
    }
}
