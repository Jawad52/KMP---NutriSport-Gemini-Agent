package com.jucode.nutrisport.router

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector? = null, val label: String? = null) {
    object Home : Screen("home", Icons.Default.Home, "Home")
    object Cart : Screen("cart", Icons.Default.ShoppingCart, "Cart")
    object Deal : Screen("deal", Icons.Default.LocalOffer, "Deals")
    object Profile : Screen("profile", Icons.Default.Person, "Profile")
    data class ProductDetails(val productId: String) : Screen("product_details")
    object Search : Screen("search")
    object Notification : Screen("notification")
}
