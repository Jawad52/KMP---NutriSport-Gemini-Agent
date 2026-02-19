package com.jucode.nutrisport.dashboard.domain

import kotlinx.coroutines.flow.Flow

class GetBannersUseCase(private val repository: BannerRepository) {
    operator fun invoke(): Flow<List<Banner>> = repository.getBanners()
}
