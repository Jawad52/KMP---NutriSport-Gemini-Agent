package com.jucode.nutrisport.cart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jucode.nutrisport.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage() {
    var promoCode by remember { mutableStateOf("") }
    
    val isAddressValid = UserSettings.mainStreet.isNotBlank() && 
            UserSettings.city.isNotBlank() && 
            UserSettings.zipCode.isNotBlank() &&
            (if (UserSettings.addressType == AddressType.OFFICE) UserSettings.buildingName.isNotBlank() else UserSettings.villaNo.isNotBlank())

    val isFormValid = CartState.items.isNotEmpty() && isAddressValid

    Scaffold(
        topBar = { TopAppBar(title = { Text("Checkout", fontWeight = FontWeight.Bold) }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 2) Cart Items
            items(CartState.items) { item ->
                CartItemRow(item)
            }

            if (CartState.items.isEmpty()) {
                item { 
                    Box(Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                        Text("Your cart is empty", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            // 3) Suggestions
            item {
                Text("Suggested for you", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(MockData.allProducts.take(5)) { product ->
                        SuggestionCard(product, {
                            // Impl in upcoming PR
                        })
                    }
                }
            }

            // 4) Address Section
            item {
                AddressSection()
            }

            // 5) Payment & Promo
            item {
                PaymentSection(promoCode, onPromoChange = { promoCode = it })
            }

            // 6) Place Order Button
            item {
                Button(
                    onClick = { /* Handle Order */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    enabled = isFormValid,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Place Order")
                }
                if (!isFormValid && CartState.items.isNotEmpty()) {
                    Text(
                        "Please fill in all address fields",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.product.imageRes,
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(item.product.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text("$${item.product.price}", color = MaterialTheme.colorScheme.primary)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { CartState.removeFromCart(item.product) }) {
                    Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(20.dp))
                }
                Text("${item.quantity}", fontWeight = FontWeight.Bold)
                IconButton(onClick = { CartState.addToCart(item.product) }) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun SuggestionCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier.width(120.dp).clickable { onClick(product) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            AsyncImage(model = product.imageRes, contentDescription = null, modifier = Modifier.size(60.dp))
            Text(product.name, style = MaterialTheme.typography.labelSmall, maxLines = 1)
        }
    }
}

@Composable
fun AddressSection() {
    Column {
        Text("Shipping Address", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Row(Modifier.padding(vertical = 8.dp)) {
            FilterChip(
                selected = UserSettings.addressType == AddressType.HOME,
                onClick = { UserSettings.addressType = AddressType.HOME },
                label = { Text("Home") },
                leadingIcon = { if(UserSettings.addressType == AddressType.HOME) Icon(Icons.Default.Home, null) }
            )
            Spacer(Modifier.width(8.dp))
            FilterChip(
                selected = UserSettings.addressType == AddressType.OFFICE,
                onClick = { UserSettings.addressType = AddressType.OFFICE },
                label = { Text("Office") },
                leadingIcon = { if(UserSettings.addressType == AddressType.OFFICE) Icon(Icons.Default.Work, null) }
            )
        }

        if (UserSettings.addressType == AddressType.OFFICE) {
            OutlinedTextField(UserSettings.buildingName, { UserSettings.buildingName = it }, label = { Text("Building Name") }, modifier = Modifier.fillMaxWidth())
        } else {
            OutlinedTextField(UserSettings.villaNo, { UserSettings.villaNo = it }, label = { Text("Villa No") }, modifier = Modifier.fillMaxWidth())
        }
        
        OutlinedTextField(UserSettings.mainStreet, { UserSettings.mainStreet = it }, label = { Text("Main Street") }, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(UserSettings.city, { UserSettings.city = it }, label = { Text("City") }, modifier = Modifier.weight(1f))
            OutlinedTextField(UserSettings.zipCode, { UserSettings.zipCode = it }, label = { Text("Zip Code") }, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(UserSettings.state, { UserSettings.state = it }, label = { Text("State") }, modifier = Modifier.weight(1f))
            OutlinedTextField(UserSettings.country, { UserSettings.country = it }, label = { Text("Country") }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PaymentSection(promoCode: String, onPromoChange: (String) -> Unit) {
    Column {
        Text("Payment & Promo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
            RadioButton(selected = true, onClick = null)
            Text("Cash on Delivery")
        }
        OutlinedTextField(
            value = promoCode,
            onValueChange = onPromoChange,
            label = { Text("Promo Code") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = { TextButton(onClick = {}) { Text("Apply") } }
        )
    }
}
