package com.jucode.nutrisport.dashboard

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class BannerDto(
    val id: String,
    val imageUrl: String,
    val title: String
)

class BannerApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getBanners(): List<BannerDto> {
        // Since there's no real free "protein banner API", 
        // I'll simulate one with high-quality protein banner URLs 
        // but structured as if it came from an API.
        // In a real scenario, you'd replace the URL with your actual endpoint.
        return listOf(
            BannerDto("1", "https://img.freepik.com/free-vector/protein-powder-banner-template_23-2148530345.jpg", "Whey Isolate Sale"),
            BannerDto("2", "https://img.freepik.com/free-psd/protein-supplement-banner-template_23-2148545163.jpg", "Mass Gainer Promo"),
            BannerDto("3", "https://img.freepik.com/free-vector/sport-supplement-horizontal-banner-template_23-2148529024.jpg", "New Pre-Workout")
        )
    }
}
