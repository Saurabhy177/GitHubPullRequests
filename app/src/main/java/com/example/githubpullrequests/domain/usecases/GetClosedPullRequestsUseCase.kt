package com.example.githubpullrequests.domain.usecases

import com.example.githubpullrequests.core.Resource
import com.example.githubpullrequests.domain.model.ClosedRequestModel
import com.example.githubpullrequests.domain.repository.GitHubRepo
import kotlinx.coroutines.flow.Flow

class GetClosedPullRequestsUseCase(
    private val repo: GitHubRepo
) {

    operator fun invoke(owner: String, repoName: String): Flow<Resource<List<ClosedRequestModel>>> {
        return repo.getClosedPullRequests()
    }
}