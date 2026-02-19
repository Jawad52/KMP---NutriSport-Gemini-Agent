package com.jucode.nutrisport.di

import com.jucode.nutrisport.dashboard.BannerApi
import com.jucode.nutrisport.dashboard.data.BannerRepositoryImpl
import com.jucode.nutrisport.dashboard.domain.BannerRepository
import com.jucode.nutrisport.dashboard.domain.GetBannersUseCase
import com.jucode.nutrisport.dashboard.presenter.DashboardViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::BannerApi)
    singleOf(::BannerRepositoryImpl) bind BannerRepository::class
    factoryOf(::GetBannersUseCase)
    factoryOf(::DashboardViewModel)
}
