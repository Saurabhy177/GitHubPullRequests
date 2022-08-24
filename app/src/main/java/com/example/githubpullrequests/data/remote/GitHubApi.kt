package com.example.githubpullrequests.data.remote

import com.example.githubpullrequests.data.remote.dto.ClosedRequestDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/repos/"
        // Created from github:
        // Settings -> Developer Settings -> Personal access tokens
        const val AUTH_TOKEN = "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"
    }

    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: token $AUTH_TOKEN"
    )
    //@GET("{owner}/{repo}/pulls?state=closed")
    @GET("{owner}/{repo}/pulls?state=all")
    suspend fun getClosedPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<ClosedRequestDto>
}