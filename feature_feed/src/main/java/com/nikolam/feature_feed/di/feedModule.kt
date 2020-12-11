package com.nikolam.feature_feed.di

import com.nikolam.feature_feed.presentation.FeedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val feedModule = module {
    viewModel { FeedViewModel(get()) }
}