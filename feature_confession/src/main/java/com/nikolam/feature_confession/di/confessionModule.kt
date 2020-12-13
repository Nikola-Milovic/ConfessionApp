package com.nikolam.feature_confession.di

import com.nikolam.feature_confession.data.ConfessionRepositoryImpl
import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_confession.domain.GetConfessionsUseCase
import com.nikolam.feature_confession.presentation.ConfessionViewModel
import com.nikolam.feature_feed.data.ConfessionDetailService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val confessionModule = module {
    viewModel { ConfessionViewModel(get()) }
    single { provideNewConfessionService(get()) }
    single<ConfessionRepository> { ConfessionRepositoryImpl(get()) }
    single { GetConfessionsUseCase(get()) }
}

fun provideNewConfessionService(retrofit: Retrofit): ConfessionDetailService {
    return retrofit.create(ConfessionDetailService::class.java)
}