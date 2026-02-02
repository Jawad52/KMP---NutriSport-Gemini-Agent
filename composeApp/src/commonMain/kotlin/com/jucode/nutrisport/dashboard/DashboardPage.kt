package com.jucode.nutrisport.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jucode.nutrisport.MockData
import com.jucode.nutrisport.dashboard.templates.CategoryRow
import com.jucode.nutrisport.dashboard.templates.DashboardHeader
import com.jucode.nutrisport.dashboard.templates.ProductSection
import com.jucode.nutrisport.dashboard.templates.PromotionSlider
import com.jucode.nutrisport.dashboard.templates.RecentOrder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage() {
    Scaffold(
        topBar = { DashboardHeader() }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { PromotionSlider() }
            item { CategoryRow() }
            item { ProductSection("Top Selling", MockData.topSellers) }
            item { RecentOrder() }
            item { ProductSection("New Products", MockData.newArrivals) }
        }
    }
}