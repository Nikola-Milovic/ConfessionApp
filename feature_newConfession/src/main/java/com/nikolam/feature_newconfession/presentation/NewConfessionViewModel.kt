package com.nikolam.feature_newconfession.presentation

import androidx.lifecycle.viewModelScope
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_newconfession.domain.usecases.SaveConfessionUseCase
import kotlinx.coroutines.launch

internal class NewConfessionViewModel(private val saveConfessionUseCase: SaveConfessionUseCase)
    : BaseViewModel<NewConfessionViewModel.ViewState, NewConfessionViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ConfessionSavingSuccess -> state.copy(
                isLoading = false,
                isError = false,
        )
        is Action.ConfessionSavingFailure -> state.copy(
                isLoading = false,
                isError = true,
        )
    }

    fun saveConfession(text: String) {
        viewModelScope.launch {
            saveConfessionUseCase.execute(text).also { result ->
                val action = when (result) {
                    is SaveConfessionUseCase.Result.Success ->
                        Action.ConfessionSavingSuccess
                    is SaveConfessionUseCase.Result.Error ->
                        Action.ConfessionSavingFailure
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
            val isLoading: Boolean = false,
            val isError: Boolean = false,
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object ConfessionSavingSuccess : Action()
        object ConfessionSavingFailure : Action()
    }


}