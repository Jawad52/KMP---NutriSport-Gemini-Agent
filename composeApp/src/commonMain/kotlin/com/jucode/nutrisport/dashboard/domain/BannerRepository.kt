package com.jucode.nutrisport.dashboard.domain

import kotlinx.coroutines.flow.Flow

interface BannerRepository {
    fun getBanners(): Flow<List<Banner>>
}
