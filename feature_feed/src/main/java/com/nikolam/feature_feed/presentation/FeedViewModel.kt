package com.nikolam.feature_feed.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.ConfessionDetailUri
import com.nikolam.common.navigation.NavManager
import com.nikolam.common.navigation.NewConfessionUri
import com.nikolam.common.navigation.UnderConstructionUri
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import com.nikolam.feature_feed.domain.GetConfessionsUseCase
import kotlinx.coroutines.launch

internal class FeedViewModel(private val navManager: NavManager, private val getConfessionsUseCase: GetConfessionsUseCase) :
        BaseViewModel<FeedViewModel.ViewState, FeedViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ConfessionsLoadingFailure -> state.copy(
                isError = true,
                isSuccess = false,
                isLoading = false
        )
        is Action.ConfessionsLoadingSuccess -> state.copy(
                isSuccess = true,
                isLoading = false,
                isError = false,
                confessions = viewAction.confessions
        )
        is Action.ConfessionsLoading -> state.copy(
                isSuccess = false,
                isLoading = true,
                isError = false
        )
    }

    fun getConfessions(sortBy: String) {
        sendAction(Action.ConfessionsLoading)
        viewModelScope.launch {
            getConfessionsUseCase.execute(sortBy).also { result ->
                when (result) {
                    is GetConfessionsUseCase.Result.Success -> {
                        if(result.confessions.isNotEmpty()) result.confessions.add(0, ConfessionDomainModel(0,"","",""))
                        sendAction(Action.ConfessionsLoadingSuccess(result.confessions))
                    }
                    is GetConfessionsUseCase.Result.Error -> sendAction(Action.ConfessionsLoadingFailure)
                }
            }
        }
    }

    fun navigateToNewConfession() {
        val uri = Uri.parse(NewConfessionUri)
        navManager.navigate(uri)
    }

    fun navigateToUnderConstruction() {
        val uri = Uri.parse(UnderConstructionUri)
        navManager.navigate(uri)
    }

    fun navigateToConfessionDetailScreen(id : String) {
        val uri = Uri.parse("$ConfessionDetailUri/?id=$id")
        navManager.navigate(uri)
    }

    internal data class ViewState(
            val isSuccess: Boolean = false,
            val isLoading: Boolean = false,
            val isError: Boolean = false,
            val confessions: List<ConfessionDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object ConfessionsLoading : Action()
        class ConfessionsLoadingSuccess(val confessions: List<ConfessionDomainModel>) : Action()
        object ConfessionsLoadingFailure : Action()
    }
}