package com.jucode.nutrisport.di

import com.jucode.nutrisport.dashboard.BannerApi
import com.jucode.nutrisport.dashboard.data.BannerRepositoryImpl
import com.jucode.nutrisport.dashboard.domain.BannerRepository
import com.jucode.nutrisport.dashboard.domain.GetBannersUseCase
import com.jucode.nutrisport.dashboard.presenter.DashboardViewModel
import com.jucode.nutrisport.router.NavigationManager
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::NavigationManager)
    singleOf(::BannerApi)
    singleOf(::BannerRepositoryImpl) bind BannerRepository::class
    factoryOf(::GetBannersUseCase)
    factoryOf(::DashboardViewModel)
}

fun initKoin(appDeclaration: org.koin.dsl.KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
    }
