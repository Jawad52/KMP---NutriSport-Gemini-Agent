package com.jucode.nutrisport.router

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart

sealed class Screen(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String) {
    object Home : Screen("home", Icons.Default.Home, "Home")
    object Cart : Screen("cart", Icons.Default.ShoppingCart, "Cart")
    object Deal : Screen("deal", Icons.Default.LocalOffer, "Deals")
    object Profile : Screen("profile", Icons.Default.Person, "Profile")
}