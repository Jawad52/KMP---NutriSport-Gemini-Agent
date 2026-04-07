package com.jucode.nutrisport.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MapView(
    modifier: Modifier = Modifier,
    onLocationSelected: (lat: Double, lng: Double) -> Unit
)
