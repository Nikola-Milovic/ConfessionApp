package com.nikolam.feature_newconfession.di


import com.nikolam.feature_newconfession.data.ConfessionRepositoryImpl
import com.nikolam.feature_newconfession.data.NewConfessionService
import com.nikolam.feature_newconfession.domain.ConfessionRepository
import com.nikolam.feature_newconfession.domain.usecases.SaveConfessionUseCase
import com.nikolam.feature_newconfession.presentation.NewConfessionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val newConfessionModule = module {
    viewModel { NewConfessionViewModel(get()) }
    single { provideNewConfessionService(get()) }
    single<ConfessionRepository> { ConfessionRepositoryImpl(get()) }
    single { SaveConfessionUseCase(get()) }
}

fun provideNewConfessionService(retrofit: Retrofit): NewConfessionService {
    return retrofit.create(NewConfessionService::class.java)
}