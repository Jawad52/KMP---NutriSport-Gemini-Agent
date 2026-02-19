package com.jucode.nutrisport

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jucode.nutrisport.di.appModule
import com.jucode.nutrisport.router.MainNavigation
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        NutriSportTheme {
            MainNavigation()
        }
    }
}
