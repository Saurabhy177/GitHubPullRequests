package com.example.githubpullrequests.data.repository

import com.example.githubpullrequests.core.Resource
import com.example.githubpullrequests.data.remote.GitHubApi
import com.example.githubpullrequests.domain.model.ClosedRequestModel
import com.example.githubpullrequests.domain.repository.GitHubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GitHubRepoImpl(
    private val api: GitHubApi
): GitHubRepo {

    override fun getClosedPullRequests(
        owner: String,
        repoName: String
    ): Flow<Resource<List<ClosedRequestModel>>> = flow {
        emit(Resource.Loading())

        try {
            val closedPullRequests = api.getClosedPullRequests(owner, repoName)
            emit(Resource.Success(
                data = closedPullRequests.map {
                    it.toClosedRequestModel()
                }
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection."
            ))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = "Oops, something went wrong!"
            ))
        }
    }
}