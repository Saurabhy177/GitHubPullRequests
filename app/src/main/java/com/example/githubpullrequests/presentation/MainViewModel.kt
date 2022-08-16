package com.example.githubpullrequests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubpullrequests.core.Resource
import com.example.githubpullrequests.domain.model.ClosedRequestModel
import com.example.githubpullrequests.domain.usecases.GetClosedPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getClosedPullRequestsUseCase: GetClosedPullRequestsUseCase
): ViewModel() {

    private val _closedPullRequests = MutableSharedFlow<List<ClosedRequestModel>>()
    val closedPullRequests = _closedPullRequests.asSharedFlow()

    private val _eventSharedFlow = MutableSharedFlow<UIEvent>()
    val eventSharedFlow = _eventSharedFlow.asSharedFlow()

    sealed class UIEvent {
        data class ShowToast(val message: String): UIEvent()
    }

    fun fetchClosedPullRequests() {
        viewModelScope.launch {
            getClosedPullRequestsUseCase(
                "Saurabhy177",
                "GitHubPullRequests\n"
            ).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        _closedPullRequests.emit(result.data ?: listOf())
                    }

                    is Resource.Error -> {
                        _eventSharedFlow.emit(
                            UIEvent.ShowToast(
                                result.message ?: ""
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}