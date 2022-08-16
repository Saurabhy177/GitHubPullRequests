package com.example.githubpullrequests.presentation

import androidx.lifecycle.ViewModel
import com.example.githubpullrequests.domain.usecases.GetClosedPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel(
    private val getClosedPullRequestsUseCase: GetClosedPullRequestsUseCase
): ViewModel() {


}