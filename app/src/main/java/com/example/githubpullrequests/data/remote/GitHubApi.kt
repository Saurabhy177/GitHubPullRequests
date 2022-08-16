package com.example.githubpullrequests.data.remote

import com.example.githubpullrequests.data.remote.dto.ClosedRequestDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/repos/"
    }

    @GET("{owner}/{repo}/pulls?state=closed")
    suspend fun getClosedPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<ClosedRequestDto>
}