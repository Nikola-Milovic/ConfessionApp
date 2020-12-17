package com.nikolam.feature_confession.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.AddCommentUri
import com.nikolam.common.navigation.ConfessionDetailUri
import com.nikolam.common.navigation.NavManager
import com.nikolam.common.viewmodel.BaseAction
import com.nikolam.common.viewmodel.BaseViewModel
import com.nikolam.common.viewmodel.BaseViewState
import com.nikolam.feature_confession.domain.models.CommentDomainModel
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel
import com.nikolam.feature_confession.domain.usecases.GetCommentsUseCase
import com.nikolam.feature_confession.domain.usecases.GetConfessionsUseCase
import kotlinx.coroutines.launch

internal class ConfessionViewModel(
    private val getConfessionsUseCase: GetConfessionsUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val navManager: NavManager
) : BaseViewModel<ConfessionViewModel.ViewState, ConfessionViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ConfessionLoadingSuccess -> state.copy(
            isSuccess = true,
            isLoading = false,
            isError = false,
            confession = viewAction.confession ?: state.confession,
            comments = viewAction.comments
        )
        is Action.ConfessionLoadingFailure -> state.copy(
            isSuccess = false,
            isLoading = false,
            isError = true,
            confession = null
        )
    }

    fun getConfession(id: String) {
        viewModelScope.launch {
            getConfessionsUseCase.execute(id).let {
                when (it) {
                    is GetConfessionsUseCase.Result.Success -> {
                        sendAction(Action.ConfessionLoadingSuccess(it.confession))
                        getComments(it.confession.id)
                    }
                    is GetConfessionsUseCase.Result.Error -> sendAction(Action.ConfessionLoadingFailure)
                }
            }
        }
    }

    private suspend fun getComments(id: String) {
        getCommentsUseCase.execute(id).let { commentResult ->
            when (commentResult) {
                is GetCommentsUseCase.Result.Success -> sendAction(
                    Action.ConfessionLoadingSuccess(
                        null,
                        comments = commentResult.comments
                    )
                )
                is GetCommentsUseCase.Result.Error -> sendAction(
                    Action.ConfessionLoadingSuccess(
                        null,
                        comments = arrayListOf()
                    )
                )
            }
        }
    }

    fun navigateToAddCommentFragment(id : String) {
        val uri = Uri.parse("$AddCommentUri/?id=$id")
        navManager.navigate(uri)
    }

    internal data class ViewState(
        val isSuccess: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val confession: ConfessionDomainModel? = null,
        val comments: ArrayList<CommentDomainModel> = arrayListOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ConfessionLoadingSuccess(
            val confession: ConfessionDomainModel?,
            val comments: ArrayList<CommentDomainModel> = arrayListOf()
        ) : Action()

        object ConfessionLoadingFailure : Action()
    }


}