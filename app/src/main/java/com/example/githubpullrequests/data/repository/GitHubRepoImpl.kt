package com.example.githubpullrequests.data.repository

import com.example.githubpullrequests.core.Resource
import com.example.githubpullrequests.data.remote.GitHubApi
import com.example.githubpullrequests.domain.model.ClosedRequestModel
import com.example.githubpullrequests.domain.repository.GitHubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitHubRepoImpl(
    private val api: GitHubApi
): GitHubRepo {

    override fun getClosedPullRequests(): Flow<Resource<List<ClosedRequestModel>>> = flow {

    }
}