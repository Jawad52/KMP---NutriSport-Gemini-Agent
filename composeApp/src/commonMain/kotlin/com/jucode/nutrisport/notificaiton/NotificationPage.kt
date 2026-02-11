package com.jucode.nutrisport.notificaiton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class NotificationItem(
    val title: String,
    val description: String,
    val date: String,
    var isExpanded: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPage(onBack: () -> Unit) {
    val notifications = remember {
        mutableStateListOf(
            NotificationItem("Order Delivered", "Your order #12345 has been delivered successfully. Enjoy your purchase!", "Oct 24, 2023"),
            NotificationItem("New Promotion", "Flash Sale is live! Get 40% off on all Wheybolic Extreme products today only.", "Oct 23, 2023"),
            NotificationItem("Stock Alert", "Your favorite Creatine Monohydrate is back in stock. Buy now before it's gone!", "Oct 22, 2023"),
            NotificationItem("Theme Updated", "We've added a new Neon Mint theme to your profile settings. Check it out!", "Oct 21, 2023")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { /* Mark all as read logic */ }) {
                        Text("Read All")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notifications) { notification ->
                var expanded by remember { mutableStateOf(notification.isExpanded) }
                NotificationCard(
                    notification = notification,
                    isExpanded = expanded,
                    onToggle = { expanded = !expanded }
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: NotificationItem,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onToggle() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = notification.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = notification.date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
