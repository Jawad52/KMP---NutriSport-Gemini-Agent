package com.jucode.nutrisport.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jucode.nutrisport.UserSettings
import com.jucode.nutrisport.profile.template.ThemeLayout

@Composable
fun ProfilePage() {
    var showEditDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                UserHeader(onEditClick = { showEditDialog = true })
            }
            item {
                UserInfoSection()
            }
            item {
                ContactUsSection()
            }
            item {
                ThemeLayout()
            }
        }

        Text(
            "App Version 1.0.0",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }

    if (showEditDialog) {
        EditProfileDialog(onDismiss = { showEditDialog = false })
    }
}

@Composable
private fun UserHeader(onEditClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = UserSettings.profileImageUri,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(UserSettings.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(UserSettings.email, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onEditClick) {
            Icon(Icons.Default.Edit, contentDescription = "Edit Profile", modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Edit Profile")
        }
    }
}

@Composable
private fun UserInfoSection() {
    Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            UserInfoRow(icon = Icons.Default.LocationOn, text = UserSettings.address)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            UserInfoRow(icon = Icons.Default.Phone, text = UserSettings.phone)
        }
    }
}

@Composable
private fun ContactUsSection() {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* TODO: Handle click */ }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.HeadsetMic, contentDescription = null)
            Spacer(Modifier.width(16.dp))
            Text("Contact Us", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}

@Composable
private fun UserInfoRow(icon: ImageVector, text: String) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun EditProfileDialog(onDismiss: () -> Unit) {
    var name by remember { mutableStateOf(UserSettings.name) }
    var address by remember { mutableStateOf(UserSettings.address) }
    var phone by remember { mutableStateOf(UserSettings.phone) }
    var email by remember { mutableStateOf(UserSettings.email) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(name, { name = it }, label = { Text("Name") })
                OutlinedTextField(address, { address = it }, label = { Text("Address") })
                OutlinedTextField(phone, { phone = it }, label = { Text("Phone") })
                OutlinedTextField(email, { email = it }, label = { Text("Email") })
                Button(onClick = {
                    // Cycle through some placeholder images
                    val newImage = "https://ui-avatars.com/api/?name=${name.replace(" ", "+")}&background=0D8ABC&color=fff"
                    UserSettings.profileImageUri = newImage
                }) {
                    Text("Change Profile Image")
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                UserSettings.name = name
                UserSettings.address = address
                UserSettings.phone = phone
                UserSettings.email = email
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
