package com.nikolam.feature_confession.di

import com.nikolam.feature_confession.data.ConfessionRepositoryImpl
import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_confession.domain.usecases.GetConfessionsUseCase
import com.nikolam.feature_confession.presentation.ConfessionViewModel
import com.nikolam.feature_confession.data.ConfessionDetailService
import com.nikolam.feature_confession.domain.usecases.GetCommentsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val confessionModule = module {
    viewModel { ConfessionViewModel(get(), get(), get()) }
    single { provideConfessionDetailService(get()) }
    single<ConfessionRepository> { ConfessionRepositoryImpl(get()) }
    single { GetConfessionsUseCase(get()) }
    single { GetCommentsUseCase(get()) }
}

fun provideConfessionDetailService(retrofit: Retrofit): ConfessionDetailService {
    return retrofit.create(ConfessionDetailService::class.java)
}