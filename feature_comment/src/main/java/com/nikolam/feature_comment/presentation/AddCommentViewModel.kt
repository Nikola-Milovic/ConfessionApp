package com.nikolam.feature_comment.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.ConfessionDetailUri
import com.nikolam.common.navigation.NavManager
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_comment.domain.PostCommentUseCase
import kotlinx.coroutines.launch

internal class AddCommentViewModel(
    private val postCommentUseCase: PostCommentUseCase,
    private val navManager: NavManager
) : BaseViewModel<AddCommentViewModel.ViewState, AddCommentViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.CommentPostingError -> state.copy(
            isSuccess = false,
            isLoading = false,
            isError = true
        )
        is Action.CommentPostingLoading -> state.copy(
            isLoading = true,
            isSuccess = false,
            isError = false
        )
        is Action.CommentPostingSuccess -> state.copy(
            isSuccess = true,
            isLoading = false,
            isError = false
        )
    }

    fun postComment(text: String, id : String) {
        sendAction(Action.CommentPostingLoading)
        viewModelScope.launch {
            postCommentUseCase.execute(text, id).let {
                when (it) {
                    is PostCommentUseCase.Result.Success -> {
                       sendAction(Action.CommentPostingSuccess)
                    }
                    is PostCommentUseCase.Result.Error -> {
                        sendAction(Action.CommentPostingError)
                    }
                }
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isSuccess : Boolean = false
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object CommentPostingError : Action()
        object CommentPostingSuccess : Action()
        object CommentPostingLoading : Action()
    }


}