package com.example.githubpullrequests.domain.repository

import com.example.githubpullrequests.core.Resource
import com.example.githubpullrequests.domain.model.ClosedRequestModel
import kotlinx.coroutines.flow.Flow

interface GitHubRepo {

    fun getClosedPullRequests(): Flow<Resource<List<ClosedRequestModel>>>
}