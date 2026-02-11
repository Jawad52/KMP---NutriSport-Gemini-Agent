package com.jucode.nutrisport.dashboard.templates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jucode.nutrisport.UserSettings


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardHeader(
    onSearchClick: () -> Boolean,
    onNotificationClick: () -> Boolean) {
    TopAppBar(
        title = {
            Text(
                "NutriSport",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        navigationIcon = {
            AsyncImage(
                model = UserSettings.profileImageUri,
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        actions = {
            IconButton(onClick = {onSearchClick.invoke()}) {
                Icon(Icons.Default.Search, "Search", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = {onNotificationClick.invoke()}) {
                Icon(Icons.Default.Notifications, "Notifications", tint = MaterialTheme.colorScheme.primary)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
