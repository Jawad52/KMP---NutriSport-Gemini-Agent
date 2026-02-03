package com.jucode.nutrisport

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.DrawableResource
import nutrisport.composeapp.generated.resources.Res
import nutrisport.composeapp.generated.resources.compose_multiplatform

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: DrawableResource
)

data class Category(
    val id: String,
    val name: String,
    val iconRes: DrawableResource
)

object UserSettings {
    var name by mutableStateOf("Jawad Usman")
    var address by mutableStateOf("123 Sport St, Fitness City")
    var phone by mutableStateOf("+1 234 567 890")
    var email by mutableStateOf("jawad.usman@jdcoding.com")
    var profileImageUri by mutableStateOf("https://ui-avatars.com/api/?name=John+Doe&background=00E5FF&color=fff")
}

object MockData {
    val categories = listOf(
        Category("1", "Protein", Res.drawable.compose_multiplatform),
        Category("2", "Vitamins", Res.drawable.compose_multiplatform),
        Category("3", "Pre-Workout", Res.drawable.compose_multiplatform),
        Category("4", "Snacks", Res.drawable.compose_multiplatform),
        Category("5", "Equipment", Res.drawable.compose_multiplatform)
    )

    val topSellers = listOf(
        Product("1", "Whey Protein", 59.99, Res.drawable.compose_multiplatform),
        Product("2", "Creatine Monohydrate", 24.99, Res.drawable.compose_multiplatform),
        Product("3", "BCAA Amino Acids", 29.99, Res.drawable.compose_multiplatform)
    )

    val newArrivals = listOf(
        Product("4", "Vegan Protein Bar", 3.50, Res.drawable.compose_multiplatform),
        Product("5", "Magnesium ZMA", 19.99, Res.drawable.compose_multiplatform),
        Product("6", "Shaker Bottle", 12.00, Res.drawable.compose_multiplatform)
    )

    val promotions = listOf(
        "Summer Sale: 20% OFF",
        "Free Shipping on orders over $50",
        "New Flavor: Tropical Punch is here!"
    )
}
