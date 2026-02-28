package com.jucode.nutrisport

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jucode.nutrisport.router.MainNavigation

@Composable
fun App() {
    NutriSportTheme {
        MainNavigation()
    }
}

@Composable
@Preview
fun AppPreview() {
    App()
}
