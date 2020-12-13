package com.nikolam.feature_confession.presentation

import androidx.lifecycle.viewModelScope
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_confession.domain.ConfessionDomainModel
import com.nikolam.feature_confession.domain.GetConfessionsUseCase
import kotlinx.coroutines.launch

internal class ConfessionViewModel(private val getConfessionsUseCase: GetConfessionsUseCase) :
    BaseViewModel<ConfessionViewModel.ViewState, ConfessionViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ConfessionLoadingSuccess -> state.copy(
            isSuccess = true,
            isLoading = false,
            isError = false,
            confession = viewAction.confession
        )
        is Action.ConfessionLoadingFailure -> state.copy(
            isSuccess = false,
            isLoading = false,
            isError = false,
            confession = null
        )
    }

    fun getConfession(id: String) {

        viewModelScope.launch {
            getConfessionsUseCase.execute(id).let {
                when(it){
                    is GetConfessionsUseCase.Result.Success -> sendAction(Action.ConfessionLoadingSuccess(it.confession))
                    is GetConfessionsUseCase.Result.Error -> sendAction(Action.ConfessionLoadingFailure)
                }
            }
        }
    }

    internal data class ViewState(
        val isSuccess: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val confession : ConfessionDomainModel? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ConfessionLoadingSuccess(val confession: ConfessionDomainModel) : Action()
        object ConfessionLoadingFailure : Action()
    }


}