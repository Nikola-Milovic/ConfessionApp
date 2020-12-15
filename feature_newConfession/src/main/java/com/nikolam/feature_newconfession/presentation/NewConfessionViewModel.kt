package com.nikolam.feature_newconfession.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.ConfessionDetailUri
import com.nikolam.common.navigation.NavManager
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_newconfession.domain.usecases.SaveConfessionUseCase
import kotlinx.coroutines.launch

internal class NewConfessionViewModel(
    private val saveConfessionUseCase: SaveConfessionUseCase,
    private val navManager: NavManager
) :
    BaseViewModel<NewConfessionViewModel.ViewState, NewConfessionViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ConfessionSavingSuccess -> state.copy(
            isSuccess = true,
            isLoading = false,
            isError = false,
            id = viewAction.id
        )
        is Action.ConfessionSavingFailure -> state.copy(
            isSuccess = false,
            isLoading = false,
            isError = true
        )
        is Action.ConfessionSavingLoading -> state.copy(
            isSuccess = false,
            isLoading = true,
            isError = false
        )
    }

    fun saveConfession(text: String) {
        sendAction(Action.ConfessionSavingLoading)
        viewModelScope.launch {
            saveConfessionUseCase.execute(text).also { result ->
                val action = when (result) {
                    is SaveConfessionUseCase.Result.Success ->
                        Action.ConfessionSavingSuccess(result.id)
                    is SaveConfessionUseCase.Result.Error ->
                        Action.ConfessionSavingFailure
                }
                sendAction(action)
            }
        }
    }

    fun navigateToConfessionDetailScreen(id: String) {
        val uri = Uri.parse("$ConfessionDetailUri/?id=$id")
        navManager.navigate(uri)
    }


    internal data class ViewState(
        val isSuccess: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val id: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ConfessionSavingSuccess(val id: String) : Action()
        object ConfessionSavingLoading : Action()
        object ConfessionSavingFailure : Action()
    }


}