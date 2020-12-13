package com.nikolam.feature_confession.presentation

import androidx.lifecycle.viewModelScope
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_newconfession.domain.usecases.SaveConfessionUseCase
import kotlinx.coroutines.launch

internal class ConfessionViewModel() :
    BaseViewModel<ConfessionViewModel.ViewState, ConfessionViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action -> state
    }

    fun saveConfession(text: String) {

        viewModelScope.launch {
        }
    }

    internal data class ViewState(
        val isSuccess: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val id: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ConfessionSavingSuccess(val id: String) : Action()
    }


}