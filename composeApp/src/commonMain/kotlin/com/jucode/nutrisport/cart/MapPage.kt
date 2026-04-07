package com.jucode.nutrisport.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPage(onBack: () -> Unit, onLocationSelected: (String) -> Unit) {
    var selectedAddress by remember { mutableStateOf("123 Fitness Ave, Gym City, NutriState 56789") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Use the actual MapView component
        MapView(
            modifier = Modifier.fillMaxSize(),
            onLocationSelected = { lat, lng ->
                // In a real app, you'd reverse geocode here
                selectedAddress = "Location: $lat, $lng"
            }
        )

        // Top Bar
        TopAppBar(
            title = { Text("Select Location", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White.copy(alpha = 0.8f)
            )
        )

        // Floating Action Button for current location
        FloatingActionButton(
            onClick = { /* Get current location logic */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 120.dp, end = 16.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Icon(Icons.Default.MyLocation, contentDescription = "Current Location")
        }
    }
}
