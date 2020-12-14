package com.nikolam.feature_confession.presentation

import androidx.lifecycle.viewModelScope
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_confession.domain.GetCommentsUseCase
import com.nikolam.feature_confession.domain.GetConfessionsUseCase
import com.nikolam.feature_confession.domain.models.CommentDomainModel
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel
import kotlinx.coroutines.launch

internal class ConfessionViewModel(private val getConfessionsUseCase: GetConfessionsUseCase,
                                   private val getCommentsUseCase: GetCommentsUseCase
) :
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
        is Action.ConfessionCommentsLoadingSuccess -> state.copy(
                comments = viewAction.comments
        )
        is Action.ConfessionCommentsLoadingFailure -> state
    }

    fun getConfession(id: String) {

        viewModelScope.launch {
            getConfessionsUseCase.execute(id).let {
                when (it) {
                    is GetConfessionsUseCase.Result.Success -> {
                        sendAction(Action.ConfessionLoadingSuccess(it.confession))
                        getCommentsUseCase.execute(id).let {commentResult ->
                            when (commentResult) {
                                is GetCommentsUseCase.Result.Success -> sendAction(Action.ConfessionCommentsLoadingSuccess(commentResult.comments))
                                is GetCommentsUseCase.Result.Error -> sendAction(Action.ConfessionCommentsLoadingFailure)
                            }
                        }
                    }
                    is GetConfessionsUseCase.Result.Error -> sendAction(Action.ConfessionLoadingFailure)
                }
            }
        }
    }

    internal data class ViewState(
            val isSuccess: Boolean = false,
            val isLoading: Boolean = false,
            val isError: Boolean = false,
            val confession: ConfessionDomainModel? = null,
            val comments: ArrayList<CommentDomainModel> = arrayListOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ConfessionLoadingSuccess(val confession: ConfessionDomainModel) : Action()
        class ConfessionCommentsLoadingSuccess(val comments: ArrayList<CommentDomainModel>) : Action()
        object ConfessionCommentsLoadingFailure : Action()
        object ConfessionLoadingFailure : Action()
    }


}