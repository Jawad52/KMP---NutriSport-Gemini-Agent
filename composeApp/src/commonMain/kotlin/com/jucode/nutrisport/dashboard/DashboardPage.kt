package com.jucode.nutrisport.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jucode.nutrisport.MockData
import com.jucode.nutrisport.Product
import com.jucode.nutrisport.dashboard.templates.*

// Persistent session state to show popup only once per app launch
private var hasShownCampaignThisSession = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(
    onProductClick: (Product) -> Unit,
    onSearchClick: () -> Boolean,
    onNotificationClick: () -> Boolean
) {
    var showCampaign by remember { mutableStateOf(!hasShownCampaignThisSession) }

    if (showCampaign) {
        CampaignPopup(onDismiss = {
            hasShownCampaignThisSession = true
        })
    }

    Scaffold(
        topBar = { DashboardHeader(onSearchClick, onNotificationClick) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(bottom = 100.dp) // Added padding for floating bottom bar
        ) {
            item { PromotionSlider(banners = MockData.banners) }
            item { CategoryRow() }
            item { 
                ProductSection(
                    title = "Top Selling", 
                    products = MockData.topSellers,
                    onProductClick = onProductClick
                ) 
            }
            item { RecentOrder() }
            item { 
                ProductSection(
                    title = "New Products", 
                    products = MockData.newArrivals,
                    onProductClick = onProductClick
                ) 
            }
        }
    }
}
