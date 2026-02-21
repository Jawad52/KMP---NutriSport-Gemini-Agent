package com.jucode.nutrisport.router

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val icon: ImageVector? = null, val label: String? = null) {
    object Login : Screen()
    object Home : Screen(Icons.Default.Home, "Home")
    object Cart : Screen(Icons.Default.ShoppingCart, "Cart")
    object Deal : Screen(Icons.Default.LocalOffer, "Deals")
    object Profile : Screen( Icons.Default.Person, "Profile")
    data class ProductDetails(val productId: String) : Screen()
    object Search : Screen()
    object Notification : Screen()
    object ChatBot : Screen()
}
