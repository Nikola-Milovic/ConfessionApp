package com.nikolam.feature_feed.di

import com.nikolam.feature_feed.data.ConfessionFeedService
import com.nikolam.feature_feed.data.ConfessionRepositoryImpl
import com.nikolam.feature_feed.domain.ConfessionRepository
import com.nikolam.feature_feed.domain.GetConfessionsUseCase
import com.nikolam.feature_feed.presentation.FeedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val feedModule = module {
    viewModel { FeedViewModel(get(), get()) }
    single { provideNewConfessionService(get()) }
    single<ConfessionRepository> { ConfessionRepositoryImpl(get()) }
    single { GetConfessionsUseCase(get()) }
}

fun provideNewConfessionService(retrofit: Retrofit): ConfessionFeedService {
    return retrofit.create(ConfessionFeedService::class.java)
}