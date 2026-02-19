package com.jucode.nutrisport.dashboard.data

import com.jucode.nutrisport.dashboard.BannerApi
import com.jucode.nutrisport.dashboard.domain.Banner
import com.jucode.nutrisport.dashboard.domain.BannerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BannerRepositoryImpl(private val api: BannerApi) : BannerRepository {
    override fun getBanners(): Flow<List<Banner>> = flow {
        val dtos = api.getBanners()
        emit(dtos.map { Banner(it.id, it.imageUrl, it.title) })
    }
}
