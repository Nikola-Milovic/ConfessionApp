package com.nikolam.feature_feed.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.NavManager
import com.nikolam.common.navigation.NewConfessionUri
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class FeedViewModel(val navManager: NavManager) :
    BaseViewModel<FeedViewModel.ViewState, FeedViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        else ->  state
    }

    fun getConfessions(sortBy : String) {
        sendAction(Action.ConfessionsLoading)
        viewModelScope.launch {

            }
        }

    fun navigateToNewConfession() {
        val uri = Uri.parse(NewConfessionUri)
        navManager.navigate(uri)
    }

    internal data class ViewState(
        val isSuccess: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val confessions : List<String> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object ConfessionsLoading : Action()
        class ConfessionsLoadingSuccess(confessions : List<String>) : Action()
        object ConfessionsLoadingFailure : Action()
    }
}