package com.jucode.nutrisport.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
actual fun MapView(
    modifier: Modifier,
    onLocationSelected: (lat: Double, lng: Double) -> Unit
) {
    val dubai = LatLng(25.2048, 55.2708)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(dubai, 10f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            onLocationSelected(latLng.latitude, latLng.longitude)
        }
    ) {
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
            title = "Selected Location"
        )
    }
}
