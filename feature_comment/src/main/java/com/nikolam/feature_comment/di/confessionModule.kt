package com.nikolam.feature_comment.di

import com.nikolam.feature_comment.data.AddCommentService
import com.nikolam.feature_comment.data.ConfessionRepositoryImpl
import com.nikolam.feature_comment.domain.ConfessionRepository
import com.nikolam.feature_comment.domain.PostCommentUseCase
import com.nikolam.feature_comment.presentation.AddCommentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val addCommentModule = module {
    viewModel { AddCommentViewModel(get(), get()) }
    single { provideAddCommentService(get()) }
    single<ConfessionRepository> { ConfessionRepositoryImpl(get()) }
    single { PostCommentUseCase(get()) }
}

fun provideAddCommentService(retrofit: Retrofit): AddCommentService {
    return retrofit.create(AddCommentService::class.java)
}