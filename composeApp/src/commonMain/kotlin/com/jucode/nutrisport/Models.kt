package com.jucode.nutrisport

import androidx.compose.runtime.*
import org.jetbrains.compose.resources.DrawableResource
import nutrisport.composeapp.generated.resources.Res
import nutrisport.composeapp.generated.resources.compose_multiplatform

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: DrawableResource,
    val rating: Double = 4.5,
    val category: String = "Supplements",
    val description: String = "Premium quality product designed to support your fitness goals. Made with high-quality ingredients for maximum effectiveness."
)

data class CartItem(
    val product: Product,
    var quantity: Int
)

data class Category(
    val id: String,
    val name: String,
    val iconRes: DrawableResource
)

enum class AddressType { HOME, OFFICE }

object UserSettings {
    var name by mutableStateOf("John Doe")
    var address by mutableStateOf("123 Sport St, Fitness City")
    var phone by mutableStateOf("+1 234 567 890")
    var email by mutableStateOf("john.doe@example.com")
    var profileImageUri by mutableStateOf("https://ui-avatars.com/api/?name=John+Doe&background=00E5FF&color=fff")

    // Address fields
    var addressType by mutableStateOf(AddressType.HOME)
    var buildingName by mutableStateOf("")
    var villaNo by mutableStateOf("")
    var mainStreet by mutableStateOf("")
    var city by mutableStateOf("")
    var zipCode by mutableStateOf("")
    var state by mutableStateOf("")
    var country by mutableStateOf("")

    // Payment
    var paymentMethod by mutableStateOf("Cash on Delivery")
}

object CartState {
    val items = mutableStateListOf<CartItem>()

    fun addToCart(product: Product) {
        val existing = items.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            items.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(product: Product) {
        val existing = items.find { it.product.id == product.id }
        if (existing != null) {
            if (existing.quantity > 1) {
                existing.quantity--
            } else {
                items.remove(existing)
            }
        }
    }
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
        Product("1", "Whey Protein", 59.99, Res.drawable.compose_multiplatform, 4.8, "Protein", "Best-selling whey protein isolate for muscle recovery."),
        Product("2", "Creatine Monohydrate", 24.99, Res.drawable.compose_multiplatform, 4.9, "Supplements", "Pure creatine monohydrate to boost strength and power."),
        Product("3", "BCAA Amino Acids", 29.99, Res.drawable.compose_multiplatform, 4.7, "Amino Acids", "Essential amino acids to reduce muscle soreness and fatigue.")
    )

    val newArrivals = listOf(
        Product("4", "Vegan Protein Bar", 3.50, Res.drawable.compose_multiplatform, 4.6, "Snacks", "Delicious plant-based protein bar for on-the-go snacking."),
        Product("5", "Magnesium ZMA", 19.99, Res.drawable.compose_multiplatform, 4.5, "Vitamins", "Support sleep quality and muscle recovery with ZMA."),
        Product("6", "Shaker Bottle", 12.00, Res.drawable.compose_multiplatform, 4.4, "Equipment", "Durable and leak-proof shaker bottle for your supplements.")
    )

    val allProducts = topSellers + newArrivals

    val promotions = listOf(
        "Summer Sale: 20% OFF",
        "Free Shipping on orders over $50",
        "New Flavor: Tropical Punch is here!"
    )

    fun getProductById(id: String?) = allProducts.find { it.id == id }
}
